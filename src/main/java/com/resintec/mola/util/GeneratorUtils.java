package com.resintec.mola.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.springframework.util.FileCopyUtils;

import com.resintec.mola.enums.ImportJavaTypeEnum;
import com.resintec.mola.enums.JdbcTypeEnum;
import com.resintec.mola.model.BusinessException;
import com.resintec.mola.model.TemplateInfo;
import com.resintec.mola.model.config.AbstractFilePathConfiguration;
import com.resintec.mola.model.config.ConnectionConfiguration;
import com.resintec.mola.model.config.FieldMappingConfiguration;
import com.resintec.mola.model.config.GenrateConfiguration;
import com.resintec.mola.model.config.SinglePathConfiguration;
import com.resintec.mola.model.config.DataSourceConfiguration;
import com.resintec.mola.model.db.Column;
import com.resintec.mola.model.db.Database;
import com.resintec.mola.model.db.Table;
import com.resintec.mola.model.function.AutoWrapFunction;

import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * util to group generations
 * 
 * @link https://github.com/woodenlock/Mola
 * @author woodenlock
 *
 */
public class GeneratorUtils{

    private static LogUtils.Log log = LogUtils.build(GeneratorUtils.class);

    /** Full paths of files to be rollbacked store here **/
    private static Set<String> ROLLBACK_PATHS = new HashSet<String>(16);

    /** default name of this project **/
    private static final String DEFAULT_PROJECT_NAME = "Mola";

    /** split sign of the jar package path **/
    private static final String JAR_RESOURCES_FILE_SPLIT = ".jar!";

    /** template directory to restore files **/
    private static String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir") + File.separator + DEFAULT_PROJECT_NAME + File.separator;

    /**
     * do generation of the all target file with default configurations
     * 
     * @param config
     */
    public static void generate(GenrateConfiguration config) {
        Assert.isTrue(null != config, "Failed to generate due to empty param:GenrateConfiguration.");
        try {
            generate(config.getAuthor(), config.getOverride(), config.getConnection(), config.getDataSource(),
                config.getPath(), config.getMappings());
        } catch (Exception e) {
            log.info("Failed to generate files.Error:{}.", e.getMessage());
            rollback();
        }
    }

    /**
     * do generation of the all target file with default configurations
     * 
     * @param author
     * @param override
     * @param connection
     * @param dataSource
     * @param path
     * @param mapping
     */
    private static void generate(String author, Boolean override, ConnectionConfiguration connection,
        DataSourceConfiguration dataSource, AbstractFilePathConfiguration path, FieldMappingConfiguration... mapping)
    {
        log.info(
            "Ready to deal with the generation.ConnectionConfiguration:{}, DataSourceConfiguration{}, AbstractFilePathConfiguration{},"
                + " FieldMappingConfiguration{}.",
            connection, dataSource, path, mapping);
        // Ready to validate params
        boolean illegal = null == connection || CommonUtils.isBlank(connection.getUrl())
            || CommonUtils.isBlank(connection.getDriverClassFullPath()) || CommonUtils.isBlank(connection.getUserName())
            || CommonUtils.isBlank(connection.getPassword());
        Assert.isTrue(!illegal, "Lack of param:ConnectionConfiguration.");
        illegal = null == dataSource || CommonUtils.isBlank(dataSource.getTargetTablePartten())
            || (CommonUtils.isBlank(dataSource.getTargetCatalogPartten())
                && CommonUtils.isBlank(dataSource.getTargetSchemaPartten()));
        Assert.isTrue(!illegal, "Lack of param:DataSourceConfiguration.");
        Assert.isTrue(null != path, "Lack of param:AbstractFilePathConfiguration.");
        path.build();
        try {
            // Ready to execute datas group
            Database database = JdbcUtils.getDataBaseInfo(connection, dataSource);
            if (null == database || null == database.getTables() || database.getTables().size() == 0) {
                throw new BusinessException("Can't find any table in the target batabase.");
            }
            List<Table> tables = database.getTables();
            List<Table> need = filterTable(tables, dataSource, mapping);
            if (null == need || need.size() == 0) {
                throw new BusinessException("Can't find any target table in the target batabase.");
            }
            log.info("Find target table:{}.", need);
            // prepare template directory to restore files
            File directory = new File(TEMP_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // init the config of freemarker
            freemarker.template.Configuration cfg =
                new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_28);
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
            cfg.setDirectoryForTemplateLoading(directory);
            // Ready to generate files
            for (Table table : need) {
                List<Field> fields = CommonUtils.getAllFields(path.getClass(), SinglePathConfiguration.class);
                if (null == fields || fields.size() == 0) {
                    throw new BusinessException("Can't find available generate targets.");
                }
                SinglePathConfiguration data = null;
                for (Field field : fields) {
                    field.setAccessible(true);
                    data = (SinglePathConfiguration)field.get(path);
                    if (null != data && data.isOutput()) {
                        generateFile(cfg, author, table, data, path, restoreTemplate(data));
                    }
                }
                log.info("Finished to generate files from table:{}.", table.getName());
            }
        } catch (
            SecurityException | IllegalArgumentException | IllegalAccessException | SQLException | IOException e)
        {
            throw new BusinessException("Failed to execute generation due to error:" + e);
        }
    }

    /**
     * generate single file
     * 
     * @param author
     * @param table
     * @param singlePath
     * @param allPath
     * @param template
     * @throws IOException
     */
    private static void generateFile(freemarker.template.Configuration config, String author, Table table,
        SinglePathConfiguration singlePath, AbstractFilePathConfiguration allPath, TemplateInfo template)
        throws IOException
    {
        boolean illegal = null == table || CommonUtils.isBlank(table.getEntityName()) || null == singlePath
            || CommonUtils.isBlank(singlePath.getTargetBasicDirectory())
            || CommonUtils.isBlank(singlePath.getTargetPackagePath())
            || CommonUtils.isBlank(singlePath.getTargetExtension()) || null == template || null == allPath;
        Assert.isTrue(!illegal,
            "failed to generate table:" + (null == table ? null : table.getName()) + " due to lack of params.");
        // Group params in need to out put
        Map<String, Object> data = new HashMap<String, Object>(16);
        List<String>columnNames = new ArrayList<String>();
        if(null != table.getColumns()){
            for (Column column : table.getColumns()) {
                columnNames.add(column.getJavaName());
            }
        }
        data.put("table", table);
        data.put("all", allPath);
        data.put("single", singlePath);
        data.put("author", CommonUtils.isBlank(author) ? DEFAULT_PROJECT_NAME : author);
        data.put("date", CommonUtils.getCurrentTime());
        data.put("split", new AutoWrapFunction());
        data.put("columnNames", columnNames);
        // Group target real full path
        String fileName = singlePath.getTargetBasicDirectory() + singlePath.getTargetPackagePath()
            + CommonUtils.stringSupperFirst(CommonUtils.lineToHump(table.getName())) + singlePath.getTargetSuffix()
            + singlePath.getTargetExtension();
        ROLLBACK_PATHS.add(fileName);
        File file = new File(fileName);
        if (!file.getParentFile().exists() && !file.getParentFile().mkdirs() && !file.createNewFile()) {
            throw new BusinessException("Failed to mkdirs of the target file:" + file.getAbsolutePath());
        }
        config.setDirectoryForTemplateLoading(new File(template.getPath()));
        try {
            config.getTemplate(template.getFileName()).process(data, new FileWriter(file));
        } catch (TemplateException e) {
            throw new BusinessException("Failed to generate single file:" + fileName
                + " due to fail to out put the target template.Error:\n" + e);
        }
        log.info("Succeed in the output of the target file:{}.Target table name:{}.", file.getCanonicalPath(),
            table.getName());
    }

    /**
     * restore the template to make sure read files from jar package
     * 
     * @param singlePath
     * @return TemplateInfo
     * 
     * @author woodenlock
     */
    private static TemplateInfo restoreTemplate(SinglePathConfiguration singlePath) {
        TemplateInfo result = null;
        boolean available = null != singlePath && !CommonUtils.isBlank(singlePath.getTemplateFileName())
            && !CommonUtils.isBlank(singlePath.getTemplateFullPath());
        int pathIndex = singlePath.getTemplateFullPath().indexOf(JAR_RESOURCES_FILE_SPLIT);
        boolean needRestore = pathIndex >= 0;
        if (available) {
            result = new TemplateInfo();
            if (needRestore) {
                String storageName = new Random().nextLong() + "";
                try {
                    File storage = new File(TEMP_DIRECTORY + storageName);
                    storage.createNewFile();
                    String sourcesPath =
                        singlePath.getTemplateFullPath().substring(pathIndex + JAR_RESOURCES_FILE_SPLIT.length());
                    ClassPathResource cpr = new ClassPathResource(sourcesPath + singlePath.getTemplateFileName());
                    byte[] bytes = FileCopyUtils.copyToByteArray(cpr.getInputStream());
                    FileCopyUtils.copy(bytes, new FileOutputStream(storage));
                    result.setFileName(storageName);
                    result.setPath(TEMP_DIRECTORY);
                } catch (IOException e) {
                    throw new BusinessException("Failed to restore the template due to exception:" + e);
                }
            } else {
                result.setFileName(singlePath.getTemplateFileName());
                result.setPath(singlePath.getTemplateFullPath());
            }
        }
        return result;
    }

    /**
     * filter table in need
     * 
     * @param tables
     * @param dataSource
     * @param mapping
     * @return
     */
    private static List<Table> filterTable(List<Table> tables, DataSourceConfiguration dataSource,
        FieldMappingConfiguration... mapping)
    {
        if (null == tables || tables.size() == 0) {
            return null;
        }
        List<Table> need = new ArrayList<Table>();
        Map<String, String> repeated = new HashMap<String, String>(128);
        Table tmp = null;
        String targetCatalog = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetCatalogPartten())) ? null
            : dataSource.getTargetCatalogPartten();
        String targetSchema = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetSchemaPartten())) ? null
            : dataSource.getTargetSchemaPartten();
        String targetTable = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetTablePartten())) ? null
            : dataSource.getTargetTablePartten();
        boolean availableTable = false;
        for (Table t : tables) {
            if (null == t || CommonUtils.isBlank(t.getName())) {
                continue;
            }
            availableTable = (CommonUtils.isBlank(t.getCatalog())
                || (null != targetCatalog && Pattern.matches(targetCatalog, t.getCatalog())))
                && (CommonUtils.isBlank(t.getSchema())
                    || (null != targetSchema && Pattern.matches(targetSchema, t.getSchema())))
                && (null != targetTable && Pattern.matches(targetTable, t.getName()));
            if (availableTable) {
                tmp = initTable(t, mapping);
                if (null == t || CommonUtils.isBlank(t.getEntityName()) || null != repeated.get(t.getEntityName())) {
                    throw new BusinessException(
                        "Can't make sure repeated target entity name in the target batabase:" + t.getEntityName());
                } else {
                    repeated.put(t.getEntityName(), t.getEntityName());
                }
                need.add(tmp);
            }
        }
        return need;
    }

    /**
     * roll back files generated
     * TODO transaction support
     */
    private static void rollback() {
        boolean act = false;
        int affected = 0;
        for (String string : ROLLBACK_PATHS) {
            act = new File(string).delete();
            if (!act) {
                throw new BusinessException(
                    "Failed to rollback target File:" + string + ".Left files needs to be deleteds:" + ROLLBACK_PATHS);
            }
            affected++;
        }
        log.info("Succeed in rolling back files.Affected:{}.", affected);
    }

    /**
     * init column info and deal with mapping of name and type
     * 
     * @param table
     * @param mappings
     * @return
     */
    private static Table initTable(Table table, FieldMappingConfiguration... mappings) {
        if (null == table || null == table.getColumns() || table.getColumns().size() == 0) {
            return null;
        }

        JdbcTypeEnum type = null;
        Column column = null;
        List<Column> columns = table.getColumns();
        Map<String, String> importMap = new HashMap<String, String>(16);
        boolean availableConfig = false;
        for (int i = 0; i < columns.size(); i++) {
            column = columns.get(i);
            type = JdbcTypeEnum.getByJdbcCode(column.getDataType());
            if (null == type) {
                throw new BusinessException("Can't find the target jdbc type.Target type code:" + column.getDataType());
            }
            column.setJavaName(CommonUtils.lineToHump(column.getName()));
            column.setJavaTypeShortName(type.getJavaShortName());
            column.setJavaTypeFullName(type.getJavaFullName());

            if (!CommonUtils.isBlank(mappings)) {
                for (FieldMappingConfiguration mapping : mappings) {
                    availableConfig = null != mapping
                        && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
                            || Pattern.matches(mapping.getSourceCatalogPartten(), column.getCatalog()))
                        && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
                            || Pattern.matches(mapping.getSourceCatalogPartten(), column.getSchema()))
                        && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
                            || Pattern.matches(mapping.getSourceCatalogPartten(), column.getTable()))
                        && (CommonUtils.isBlank(mapping.getSourceColumnNamePartten())
                            || Pattern.matches(mapping.getSourceColumnNamePartten(), column.getName()))
                        && (CommonUtils.isBlank(mapping.getSourceColumnFullType())
                            || Pattern.matches(mapping.getSourceColumnFullType(), column.getTypeName()));
                    if (availableConfig) {
                        if (!CommonUtils.isBlank(mapping.getTargetObjectName())) {
                            column.setJavaName(mapping.getTargetObjectName());
                        }
                        if (null != mapping.getTargetObjectFullType()) {
                            column.setJavaTypeShortName(mapping.getTargetObjectFullType().getJavaShortName());
                            column.setJavaTypeFullName(mapping.getTargetObjectFullType().getJavaFullName());
                        }
                    }
                }
            }
            for (ImportJavaTypeEnum impo : ImportJavaTypeEnum.values()) {
                if (impo.getFullName().equals(column.getJavaTypeFullName())) {
                    importMap.put(impo.getFullName(), impo.getFullName());
                }
            }
            columns.set(i, column);
            if (Boolean.TRUE.equals(column.getIsPrimary())) {
                table.setPrimaryColumn(column);
            }
        }
        table.setColumns(columns);
        table.setImports(importMap);
        table.setEntityName(CommonUtils.lineToHump(table.getName()));

        return table;
    }
}