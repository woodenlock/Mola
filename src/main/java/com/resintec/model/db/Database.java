package com.resintec.model.db;

import java.io.Serializable;
import java.util.List;

/**
 * target database
 * 
 * @author woodenlock
 *
 */
public class Database implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * known user name
     */
    private String userName;

    /**
     * known user system functions
     */
    private String systemFunctions;

    /**
     * time functions
     */
    private String timeFunctions;

    /**
     * String split char
     */
    private String stringFunction;

    /**
     * main term for schema
     */
    private String schemaTerm;

    /**
     * url
     */
    private String url;

    /**
     * wether is read only
     */
    private Boolean isReadOnly;

    /**
     * name of the basic product
     */
    private String productName;

    /**
     * version of the basic product
     */
    private String productVersion;

    /**
     * name of the driver
     */
    private String driverName;

    /**
     * version of the driver
     */
    private String driverVersion;

    /**
     * all tables types used in this database
     */
    private List<String> tableTypes;

    /**
     * all tables in this database
     */
    private List<Table> tables;

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the systemFunctions
     */
    public String getSystemFunctions() {
        return systemFunctions;
    }

    /**
     * @param systemFunctions
     */
    public void setSystemFunctions(String systemFunctions) {
        this.systemFunctions = systemFunctions;
    }

    /**
     * @return the timeFunctions
     */
    public String getTimeFunctions() {
        return timeFunctions;
    }

    /**
     * @param timeFunctions
     */
    public void setTimeFunctions(String timeFunctions) {
        this.timeFunctions = timeFunctions;
    }

    /**
     * @return the stringFunction
     */
    public String getStringFunction() {
        return stringFunction;
    }

    /**
     * @param stringFunction
     */
    public void setStringFunction(String stringFunction) {
        this.stringFunction = stringFunction;
    }

    /**
     * @return the schemaTerm
     */
    public String getSchemaTerm() {
        return schemaTerm;
    }

    /**
     * @param schemaTerm
     */
    public void setSchemaTerm(String schemaTerm) {
        this.schemaTerm = schemaTerm;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the isReadOnly
     */
    public Boolean getIsReadOnly() {
        return isReadOnly;
    }

    /**
     * @param isReadOnly
     */
    public void setIsReadOnly(Boolean isReadOnly) {
        this.isReadOnly = isReadOnly;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productVersion
     */
    public String getProductVersion() {
        return productVersion;
    }

    /**
     * @param productVersion
     */
    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    /**
     * @return the driverName
     */
    public String getDriverName() {
        return driverName;
    }

    /**
     * @param driverName
     */
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    /**
     * @return the driverVersion
     */
    public String getDriverVersion() {
        return driverVersion;
    }

    /**
     * @param driverVersion
     */
    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }

    /**
     * @return the tableTypes
     */
    public List<String> getTableTypes() {
        return tableTypes;
    }

    /**
     * @param tableTypes
     */
    public void setTableTypes(List<String> tableTypes) {
        this.tableTypes = tableTypes;
    }

    /**
     * @return the tables
     */
    public List<Table> getTables() {
        return tables;
    }

    /**
     * @param tables
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Database [userName=");
        builder.append(userName);
        builder.append(", systemFunctions=");
        builder.append(systemFunctions);
        builder.append(", timeFunctions=");
        builder.append(timeFunctions);
        builder.append(", stringFunction=");
        builder.append(stringFunction);
        builder.append(", schemaTerm=");
        builder.append(schemaTerm);
        builder.append(", url=");
        builder.append(url);
        builder.append(", isReadOnly=");
        builder.append(isReadOnly);
        builder.append(", productName=");
        builder.append(productName);
        builder.append(", productVersion=");
        builder.append(productVersion);
        builder.append(", driverName=");
        builder.append(driverName);
        builder.append(", driverVersion=");
        builder.append(driverVersion);
        builder.append(", tableTypes=");
        builder.append(tableTypes);
        builder.append(", tables=");
        builder.append(tables);
        builder.append("]");
        return builder.toString();
    }
}
