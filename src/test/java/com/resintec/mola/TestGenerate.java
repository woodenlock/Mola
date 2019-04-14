package com.resintec.mola;

import com.resintec.mola.model.config.AbstractFilePathConfiguration;
import com.resintec.mola.model.config.ConnectionConfiguration;
import com.resintec.mola.model.config.DataSourceConfiguration;
import com.resintec.mola.util.GeneratorUtils;

/**
 * a test case
 * @author woodenlock
 *
 */
public class TestGenerate{
    public static void main(String[] args) {
        ConnectionConfiguration connection = new ConnectionConfiguration("jdbc:mysql://127.0.0.1:3306/resintec?serverTimezone=GMT%2B8",
            "root", "root", "com.mysql.cj.jdbc.Driver", null);
        //DataSourceConfiguration generate = new DataSourceConfiguration("resintec", null, "^menu\\w*");
        DataSourceConfiguration generate = new DataSourceConfiguration("resintec", null, "user");
        TestPathConfiguration path = new TestPathConfiguration();
        // Don't forget to build to!
        path.build();
        //FieldMappingConfiguration mapping = new FieldMappingConfiguration(null, null, null, "^add\\w*", null, null);
        GeneratorUtils.generate(connection, generate, path);
    }
    
    public static class TestPathConfiguration extends AbstractFilePathConfiguration {
        private static final long serialVersionUID = 1L;
    }
}