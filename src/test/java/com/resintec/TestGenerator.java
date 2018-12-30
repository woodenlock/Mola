package com.resintec;

import java.io.IOException;
import java.sql.SQLException;

import com.resintec.model.config.AbstractFilePathConfiguration;
import com.resintec.model.config.ConnectionConfiguration;
import com.resintec.model.config.DataSourceConfiguration;
import com.resintec.model.config.FieldMappingConfiguration;
import com.resintec.util.GeneratorUtils;

import freemarker.template.TemplateException;

/**
 * a test case
 * @author woodenlock
 *
 */
public class TestGenerator{
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException, TemplateException {
        ConnectionConfiguration connection = new ConnectionConfiguration("jdbc:mysql://127.0.0.1:3306/resintec?serverTimezone=GMT%2B8",
                "root", "root", "com.mysql.cj.jdbc.Driver", null);
            //DataSourceConfiguration generate = new DataSourceConfiguration("resintec", null, "^menu\\w*");
            DataSourceConfiguration generate = new DataSourceConfiguration("resintec", null, "device_location");
            TestPathConfiguration path = new TestPathConfiguration();
            //FieldMappingConfiguration mapping = new FieldMappingConfiguration(null, null, null, "^add\\w*", null, null);
            GeneratorUtils.generate(connection, generate, path, null);
    }
    
    public static class TestPathConfiguration extends AbstractFilePathConfiguration {
        private static final long serialVersionUID = 1L;
    }
}
