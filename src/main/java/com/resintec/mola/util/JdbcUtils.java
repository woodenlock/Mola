package com.resintec.mola.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import com.resintec.mola.enums.JdbcTypeEnum;
import com.resintec.mola.model.BusinessException;
import com.resintec.mola.model.config.ConnectionConfiguration;
import com.resintec.mola.model.config.DataSourceConfiguration;
import com.resintec.mola.model.db.Column;
import com.resintec.mola.model.db.Database;
import com.resintec.mola.model.db.Table;
import com.resintec.mola.model.db.TableIndex;

/**
 * operation of JDBC DatabaseMetaData
 * @author: woodenlock
 */
public class JdbcUtils{
	/** the catalog belongs **/
    private static final String TABLE_CAT = "TABLE_CAT";
    
    /** the schema belongs **/
    private static final String TABLE_SCHEM = "TABLE_SCHEM";
    
    /** the name of the table **/
    private static final String TABLE_NAME = "TABLE_NAME";
    
    /** the column name of the table **/
    private static final String COLUMN_NAME = "COLUMN_NAME";
    
    /** the regx string of the sql naming **/
    private static final String DATABASE_NAME_REGX = "^\\w+$";
    
    /**
     * get info of the full database
     * @param connectionConfiguration
     * @return
     * @throws SQLException
     */
    public static Database getDataBaseInfo(ConnectionConfiguration connectionConfiguration, DataSourceConfiguration dataSource)
        throws SQLException {
        if (null == connectionConfiguration || CommonUtils.isBlank(connectionConfiguration.getUrl())
            || CommonUtils.isBlank(connectionConfiguration.getUserName()) || CommonUtils.isBlank(connectionConfiguration.getPassword())
            || CommonUtils.isBlank(connectionConfiguration.getDriverClassFullPath())) {
            throw new IllegalArgumentException("Failed to get database info due to lack of params.");
        }
        
        //get the instance of the driver for connecting to database
        Driver driver = null;
        try {
            Class<?> driverClazz = Class.forName(connectionConfiguration.getDriverClassFullPath());
            driver = (Driver)driverClazz.newInstance();
            if(null == driver){
                throw new BusinessException("Failed to get database info due to fail to get the instance.");
            }
        } catch (Exception e) {
            throw new BusinessException("Failed to get database info due to fail to get the instance.Error:\n" + e);
        }
        
        //group connection config
        Properties props = new Properties();
        props.setProperty("user", connectionConfiguration.getUserName());
        props.setProperty("password", connectionConfiguration.getPassword());
        props.setProperty("remarks", "true");
        props.setProperty("useInformationSchema", "true");
        Connection connection = driver.connect(connectionConfiguration.getUrl(), props);
        
        //ready to get datas
        DatabaseMetaData dbmd = connection.getMetaData();
        Database database = getDatabaseBasicInfo(dbmd);
        if(null == database) {
        	throw new BusinessException("Failed to get database basic info due to empty return.Target DatabaseMetaData:" + dbmd);
        }
        
        List<Table>tables = getTables(dbmd, dataSource);
        database.setTables(tables);
        
        connection.close();
        return database;
    }
    
    /**
     * get original ResultSet of target tables
     * @param dbmd
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private static ResultSet getTableResultSet(DatabaseMetaData dbmd, DataSourceConfiguration dataSource) throws SQLException {
    	if(null == dbmd) {
    		return null;
    	}
    	//optimization for limited name
    	String catalog = null == dataSource ? null : dataSource.getTargetCatalogPartten();
    	if(CommonUtils.isBlank(catalog) || !Pattern.matches(DATABASE_NAME_REGX, catalog)) {
    		catalog = null;
    	}
    	String schema = null == dataSource ? null : dataSource.getTargetSchemaPartten();
    	if(CommonUtils.isBlank(schema) || !Pattern.matches(DATABASE_NAME_REGX, schema)) {
    		schema = null;
    	}
    	String tableName = null == dataSource ? null : dataSource.getTargetTablePartten();
    	if(CommonUtils.isBlank(tableName) || !Pattern.matches(DATABASE_NAME_REGX, tableName)) {
    		tableName = null;
    	}
    	return dbmd.getTables(catalog, schema, tableName, new String[] {"TABLE"});
    }
    
    /**
     * group all table infos in need
     * @param dbmd
     * @param dataSource
     * @return
     * @throws SQLException
     */
    private static List<Table> getTables(DatabaseMetaData dbmd, DataSourceConfiguration dataSource) throws SQLException{
    	if(null == dbmd) {
    		return null;
    	}
    	ResultSet rs = getTableResultSet(dbmd, dataSource);
    	if(null == rs) {
    		return null;
    	}
    	
    	Table table = null;
        ResultSet PKResultSet = null;
        ResultSet indexSet = null;
        ResultSet columnSet = null;
        List<Table> tables = new ArrayList<Table>();
        Map<String, String> primarys = null;
        Column column = null;
        List<Column> columns = null;
        while(rs.next()) {
            //Basic info of the table
            table = new Table();
            table.setCatalog(rs.getString(TABLE_CAT));
            table.setSchema(rs.getString(TABLE_SCHEM));
            table.setName(rs.getString(TABLE_NAME));
            table.setType(rs.getString("TABLE_TYPE"));
            table.setRemarks(rs.getString("REMARKS"));
            
            //Primary keys
            primarys = new HashMap<String, String>(128);
            PKResultSet =
                dbmd.getPrimaryKeys(rs.getString(TABLE_CAT), rs.getString(TABLE_SCHEM), rs.getString(TABLE_NAME));
            while(PKResultSet.next()) {
                primarys.put(PKResultSet.getString(COLUMN_NAME), PKResultSet.getString(COLUMN_NAME));
            }
            
            //indexes of the table
            indexSet = dbmd.getIndexInfo(rs.getString(TABLE_CAT), rs.getString(TABLE_SCHEM),
                rs.getString(TABLE_NAME), false, true);
            table.setIndexes(getTableIndexes(indexSet));
            
            //Every single column
            columns = new ArrayList<Column>();
            JdbcTypeEnum jdbcType = null;
            columnSet = dbmd.getColumns(rs.getString(TABLE_CAT), rs.getString(TABLE_SCHEM),
                rs.getString(TABLE_NAME), null);
            while(columnSet.next()) {
                column = new Column();
                column.setCatalog(rs.getString(TABLE_CAT));
                column.setSchema(rs.getString(TABLE_SCHEM));
                column.setTable(rs.getString(TABLE_NAME));
                column.setName(columnSet.getString(COLUMN_NAME));
                column.setDataType(columnSet.getInt("DATA_TYPE"));
                // We need to get jdbc name by matched jdbc code to combine types with different mysql names but the same target java mapping
                //column.setTypeName(columnSet.getString("TYPE_NAME"));
                jdbcType = JdbcTypeEnum.getByJdbcCode(column.getDataType());
                if(null == jdbcType){
                    throw new BusinessException("Failed to get tables from target database due to unknown jdbc code:"
                + column.getDataType() + " in table:" + table.getName());
                }
                column.setTypeName(jdbcType.getJdbcName());
                column.setSize(columnSet.getInt("COLUMN_SIZE"));
                column.setDecimalDigits(columnSet.getInt("DECIMAL_DIGITS"));
                column.setNumPercRadix(columnSet.getInt("NUM_PREC_RADIX"));
                column.setNullable("NO".equals(columnSet.getString("IS_NULLABLE")));
                if (null != primarys.get(columnSet.getString(COLUMN_NAME))) {
                    column.setIsPrimary(Boolean.TRUE);
                }
                column.setIsAutoIncrement("YES".equals(columnSet.getString("IS_AUTOINCREMENT")));
                column.setRemarks(columnSet.getString("REMARKS"));
                column.setDefaultValue(columnSet.getObject("COLUMN_DEF"));
                column.setCharOctetLength(columnSet.getInt("CHAR_OCTET_LENGTH"));
                column.setOrdinalPosition(columnSet.getInt("ORDINAL_POSITION"));
                column.setScopeSchema(columnSet.getString("SCOPE_SCHEMA"));
                column.setScopeTable(columnSet.getString("SCOPE_TABLE"));
                column.setSourceDataType(columnSet.getShort("SOURCE_DATA_TYPE"));

                columns.add(column);
            }

            table.setColumns(columns);
            tables.add(table);
        }
    	
        PKResultSet.close();
        columnSet.close();
        indexSet.close();
        rs.close();
        
    	return tables;
    }
    
    /**
     * group basic information of the database from DatabaseMetaData
     * @param dbmd
     * @return
     * @throws SQLException
     */
    private static Database getDatabaseBasicInfo(DatabaseMetaData dbmd) throws SQLException {
    	if(null == dbmd) {
    		return null;
    	}
    	ResultSet rs = null;
    	Database database = new Database();
    	database.setUserName(dbmd.getUserName());
        database.setSystemFunctions(dbmd.getSystemFunctions());
        database.setTimeFunctions(dbmd.getTimeDateFunctions());
        database.setStringFunction(dbmd.getStringFunctions());
        database.setSchemaTerm(dbmd.getSchemaTerm());
        database.setUrl(dbmd.getURL());
        database.setIsReadOnly(Boolean.valueOf(dbmd.isReadOnly()));
        database.setProductName(dbmd.getDatabaseProductName());
        database.setProductVersion(dbmd.getDatabaseProductVersion());
        database.setDriverName(dbmd.getDriverName());
        database.setDriverVersion(dbmd.getDriverVersion());

        rs = dbmd.getTableTypes();
        List<String> types = new ArrayList<String>();
        while(rs.next()) {
            types.add(rs.getString("TABLE_TYPE"));
        }
        database.setTableTypes(types);
        return database;
    }
    
    /**
     * read table indexes from database
     * @param indexSet
     * @return
     * @throws SQLException
     */
    private static List<TableIndex> getTableIndexes(ResultSet indexSet) throws SQLException{
    	if(null != indexSet) {
    		List<TableIndex> indexes = new ArrayList<TableIndex>();
    		TableIndex index = new TableIndex();
    		while(indexSet.next()) {
                index = new TableIndex();
                index.setName(indexSet.getString("INDEX_NAME"));
                index.setColumnName(indexSet.getString(COLUMN_NAME));
                index.setType(indexSet.getShort("TYPE"));
                index.setCardinality(indexSet.getInt("CARDINALITY"));
                index.setFilter(indexSet.getString("FILTER_CONDITION"));
                index.setForceUnique(Boolean.FALSE.equals(indexSet.getBoolean("NON_UNIQUE")));
                index.setOrder(indexSet.getString("ASC_OR_DESC"));
                index.setPages(indexSet.getInt("PAGES"));
                index.setPosition(indexSet.getShort("ORDINAL_POSITION"));
                index.setQualifier(indexSet.getString("INDEX_QUALIFIER"));
                indexes.add(index);
            }
    		if(indexes.size() != 0) {
    			return indexes;
    		}
    	}
    	return null;
    }
}
