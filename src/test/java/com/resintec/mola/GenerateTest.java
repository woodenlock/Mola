package com.resintec.mola;

import com.resintec.mola.model.config.AbstractFilePathConfiguration;
import com.resintec.mola.model.config.ConnectionConfiguration;
import com.resintec.mola.model.config.DataSourceConfiguration;
import com.resintec.mola.model.config.GenrateConfiguration;
import com.resintec.mola.util.GeneratorUtils;

/**
 * a test case
 * @author woodenlock
 *
 */
public class GenerateTest{
    public static void main(String[] args) {
    	GenrateConfiguration config = new GenrateConfiguration();
        config.setAuthor("Mola");
        config.setOverride(false);
        
        ConnectionConfiguration connection = new ConnectionConfiguration("jdbc:mysql://127.0.0.1:3306/resintec?serverTimezone=GMT%2B8",
        		"root", "root", "com.mysql.cj.jdbc.Driver", null);
        config.setConnection(connection);
        
        DataSourceConfiguration source = new DataSourceConfiguration("resintec", null, "user");
        //DataSourceConfiguration source = new DataSourceConfiguration("resintec", null, "^menu\\w*");
        config.setDataSource(source);
        
        TestPathConfiguration path = new TestPathConfiguration();
        path.setBusinessPackage("com/resintec/test/");
        path.build();
        path.getDao().setTargetPackagePath(path.getBusinessPackage() + "dao/mapper/");
        path.getDao().setTargetSuffix("Mapper");
        config.setPath(path);
        
//        FieldMappingConfiguration mapping = new FieldMappingConfiguration("BLOB", JdbcTypeEnum.VARCHAR);
//        config.setMappings(new FieldMappingConfiguration[]{mapping});
        
        GeneratorUtils.generate(config);
    }
    
    public static class TestPathConfiguration extends AbstractFilePathConfiguration {
        private static final long serialVersionUID = 1L;
    }
}