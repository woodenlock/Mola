package com.resintec.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.resintec.enums.ImportJavaTypeEnum;
import com.resintec.enums.JdbcTypeEnum;
import com.resintec.model.config.AbstractFilePathConfiguration;
import com.resintec.model.config.ConnectionConfiguration;
import com.resintec.model.config.FieldMappingConfiguration;
import com.resintec.model.config.SinglePathConfiguration;
import com.resintec.model.config.DataSourceConfiguration;
import com.resintec.model.db.Column;
import com.resintec.model.db.Database;
import com.resintec.model.db.Table;

import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * util to group generations
 * @author woodenlock
 *
 */
public class GeneratorUtils {
	private static LogUtils.Log log = LogUtils.build(GeneratorUtils.class);
	
	/**
	 * do generation of the all target file with default configurations
	 * @param connection
	 * @param dataSource
	 * @param path
	 * @param mapping
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws IOException
	 * @throws TemplateException
	 * TODO 1、生成的service、mapper文件没有完全驼峰
	 */
	public static void generate(ConnectionConfiguration connection, DataSourceConfiguration dataSource, AbstractFilePathConfiguration path, FieldMappingConfiguration... mapping)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException, TemplateException {
		log.info("Ready to deal with the generation.ConnectionConfiguration:{},DataSourceConfiguration{},AbstractFilePathConfiguration{},FieldMappingConfiguration{}.",
				connection, dataSource, path, mapping);
		
		if(null == connection || CommonUtils.isBlank(connection.getUrl()) || CommonUtils.isBlank(connection.getDriverClassFullPath())
		    || CommonUtils.isBlank(connection.getUserName()) || CommonUtils.isBlank(connection.getPassword())){
		    throw new IllegalArgumentException("Lack of param:ConnectionConfiguration.");
		}
		if(null == dataSource || CommonUtils.isBlank(dataSource.getTargetTablePartten())
		    || (CommonUtils.isBlank(dataSource.getTargetCatalogPartten()) && CommonUtils.isBlank(dataSource.getTargetSchemaPartten()))){
		    throw new IllegalArgumentException("Lack of param:DataSourceConfiguration.");
		}
		if(null == path){
		    throw new IllegalArgumentException("Lack of param:AbstractFilePathConfiguration.");
		}
		
        Database database = JdbcUtils.getDataBaseInfo(connection, dataSource);
        if(null == database || null == database.getTables() || database.getTables().size() == 0){
            throw new RuntimeException("Can't find any table in the target batabase.");
        }
        List<Table>tables = database.getTables();
        List<Table>need = filterTable(tables, dataSource, mapping);
        if(null == need || need.size() == 0){
            throw new RuntimeException("Can't find any target table in the target batabase.");
        }
        log.info("Find target table:{}.", need);
        
        for (Table table : need) {
            Map<String, Object> params = new HashMap<String, Object>(4);
            params.put("table", table);
            params.put("path", path);
            
            List<Field>fields = CommonUtils.getAllFields(path.getClass(), SinglePathConfiguration.class);
            if(null == fields) {
                throw new RuntimeException("Can't find available generate targets.");
            }
            SinglePathConfiguration data = null;
            for (Field field : fields) {
                field.setAccessible(true);
                data = (SinglePathConfiguration)field.get(path);
                if(null != data && data.isOutput()) {
                    generateFile(table, data, path);
                }
            }
            log.info("Finished to generate files from table:{}.", table.getName());
        }
	}
	
	/**
	 * generate single file
	 * @param table
	 * @param singlePath
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void generateFile(Table table, SinglePathConfiguration singlePath, AbstractFilePathConfiguration allPath) throws IOException, TemplateException{
	    if(null == table || CommonUtils.isBlank(table.getEntityName()) || null == singlePath || CommonUtils.isBlank(singlePath.getTargetBasicDirectory())
	        || CommonUtils.isBlank(singlePath.getTargetPackagePath()) || CommonUtils.isBlank(singlePath.getTargetExtension())
	        || CommonUtils.isBlank(singlePath.getTemplateFullPath()) || CommonUtils.isBlank(singlePath.getTemplateFileName()) || null == allPath){
	        throw new IllegalArgumentException("failed to generate table:" + (null == table ? null : table.getName()) + " due to lack of params.");
	    }
	    
	    freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
	    cfg.setDirectoryForTemplateLoading(new File(singlePath.getTemplateFullPath()));
	    cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        Map<String, Object>data = new HashMap<String, Object>(4);
        data.put("table", table);
        data.put("all", allPath);
        data.put("single", singlePath);
        data.put("author", "generator");
        data.put("date", CommonUtils.getCurrentTime());
        String fileName = singlePath.getTargetBasicDirectory() + singlePath.getTargetPackagePath() + CommonUtils.stringSupperFirst(CommonUtils.lineToHump(table.getName())) + singlePath.getTargetSuffix() + singlePath.getTargetExtension();
        File file = new File(fileName);
        if(!file.getParentFile().exists() && !file.getParentFile().mkdirs() && !file.createNewFile()) {
        	throw new RuntimeException("Failed to mkdirs of the target file:" + file.getAbsolutePath());
        }
        cfg.getTemplate(singlePath.getTemplateFileName()).process(data, new FileWriter(file));
        log.info("Succeed in output the target file:{}.Target table name:{}.", file.getAbsolutePath(), table.getName());
	}
	
	/**
	 * filter table in need
	 * @param tables
	 * @param dataSource
	 * @param mapping
	 * @return
	 */
	private static List<Table> filterTable(List<Table>tables, DataSourceConfiguration dataSource, FieldMappingConfiguration... mapping) {
		if(null == tables || tables.size() == 0) {
			return null;
		}
		List<Table>need = new ArrayList<Table>();
        Map<String, String>repeated = new HashMap<String, String>(128);
        Table tmp = null;
		String targetCatalog = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetCatalogPartten())) ? null : dataSource.getTargetCatalogPartten();
        String targetSchema = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetSchemaPartten())) ? null : dataSource.getTargetSchemaPartten();
        String targetTable = (null == dataSource || CommonUtils.isBlank(dataSource.getTargetTablePartten())) ? null : dataSource.getTargetTablePartten();
        for (Table t : tables) {
            if(null == t || CommonUtils.isBlank(t.getName())){
                continue;
            }
            if((CommonUtils.isBlank(t.getCatalog()) || (null != targetCatalog && Pattern.matches(targetCatalog, t.getCatalog())))
            		&& (CommonUtils.isBlank(t.getSchema()) || (null != targetSchema && Pattern.matches(targetSchema, t.getSchema())))
                && (null != targetTable && Pattern.matches(targetTable, t.getName()))){
            	tmp = initTable(t, mapping);
            	if(null == t || CommonUtils.isBlank(t.getEntityName()) || null != repeated.get(t.getEntityName())) {
                	throw new RuntimeException("Can't make sure repeated target entity name in the target batabase:" + t.getEntityName());
                }else {
                	repeated.put(t.getEntityName(), t.getEntityName());
                }
                need.add(tmp);
            }
        }
		return need;
	}
	
	/**
	 * init column info and deal with mapping of name and type
	 * @param columns
	 * @param mappings
	 * @return
	 */
	private static Table initTable(Table table, FieldMappingConfiguration... mappings){
	    if(null == table || null == table.getColumns() || table.getColumns().size() == 0){
	        return null;
	    }
	    
	    JdbcTypeEnum type = null;
	    Column column = null;
	    List<Column>columns = table.getColumns();
	    Map<String, String>importMap = new HashMap<String, String>();
	    for (int i = 0; i < columns.size(); i++) {
	    	column = columns.get(i);
	    	type = JdbcTypeEnum.getByJdbcCode(column.getDataType());
	    	if(null == type) {
	    		throw new RuntimeException("Can't find the target jdbc type.Target type code:" + column.getDataType());
	    	}
	    	column.setJavaName(CommonUtils.lineToHump(column.getName()));
	    	column.setJavaTypeShortName(type.getJavaShortName());
	    	column.setJavaTypeFullName(type.getJavaFullName());
	    	
	    	if(!CommonUtils.isBlank(mappings)) {
	    		for (FieldMappingConfiguration mapping : mappings) {
		            if(null != mapping && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
		                || Pattern.matches(mapping.getSourceCatalogPartten(), column.getCatalog()))
		                && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
		                    || Pattern.matches(mapping.getSourceCatalogPartten(), column.getSchema()))
		                && (CommonUtils.isBlank(mapping.getSourceCatalogPartten())
		                    || Pattern.matches(mapping.getSourceCatalogPartten(), column.getTable()))
                        && (CommonUtils.isBlank(mapping.getSourceColumnNamePartten())
                            || Pattern.matches(mapping.getSourceColumnNamePartten(), column.getName()))
                        && (CommonUtils.isBlank(mapping.getSourceColumnFullType())
                            || Pattern.matches(mapping.getSourceColumnFullType(), column.getTypeName()))){
		                if(!CommonUtils.isBlank(mapping.getTargetObjectName())){
		                    column.setJavaName(mapping.getTargetObjectName());
		                }
		                if(null != mapping.getTargetObjectFullType()){
		                    column.setJavaTypeShortName(mapping.getTargetObjectFullType().getJavaShortName());
		                    column.setJavaTypeFullName(mapping.getTargetObjectFullType().getJavaFullName());
		                }
		            }
	            }
	    	}
	        for (ImportJavaTypeEnum impo : ImportJavaTypeEnum.values()) {
				if(impo.getFullName().equals(column.getJavaTypeFullName())) {
					importMap.put(impo.getFullName(), impo.getFullName());
				}
			}
	        columns.set(i, column);
	        if(Boolean.TRUE.equals(column.getIsPrimary())){
	            table.setPrimaryColumn(column);
	        }
        }
	    table.setColumns(columns);
	    table.setImports(importMap);
	    table.setEntityName(CommonUtils.lineToHump(table.getName()));
	    
	    return table;
	}
}