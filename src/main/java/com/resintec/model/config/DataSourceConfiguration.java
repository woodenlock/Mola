package com.resintec.model.config;

import java.io.Serializable;

/**
 * target datasource ready to generate from
 * @author woodenlock
 *
 */
public class DataSourceConfiguration implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * target catalog
     */
    private String targetCatalogPartten;

    /**
     * target catalog
     */
    private String targetSchemaPartten;

    /**
     * target catalog
     */
    private String targetTablePartten;

    public DataSourceConfiguration() {
    }

    public DataSourceConfiguration(String targetCatalogPartten, String targetSchemaPartten, String targetTablePartten) {
        this.targetCatalogPartten = targetCatalogPartten;
        this.targetSchemaPartten = targetSchemaPartten;
        this.targetTablePartten = targetTablePartten;
    }

    /**
     * @return the targetCatalogPartten
     */
    public String getTargetCatalogPartten() {
        return targetCatalogPartten;
    }

    /**
     * @param targetCatalogPartten
     */
    public void setTargetCatalogPartten(String targetCatalogPartten) {
        this.targetCatalogPartten = targetCatalogPartten;
    }

    /**
     * @return the targetSchemaPartten
     */
    public String getTargetSchemaPartten() {
        return targetSchemaPartten;
    }

    /**
     * @param targetSchemaPartten
     */
    public void setTargetSchemaPartten(String targetSchemaPartten) {
        this.targetSchemaPartten = targetSchemaPartten;
    }

    /**
     * @return the targetTablePartten
     */
    public String getTargetTablePartten() {
        return targetTablePartten;
    }

    /**
     * @param targetTablePartten
     */
    public void setTargetTablePartten(String targetTablePartten) {
        this.targetTablePartten = targetTablePartten;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DataSourceConfiguration [targetCatalogPartten=");
        builder.append(targetCatalogPartten);
        builder.append(", targetSchemaPartten=");
        builder.append(targetSchemaPartten);
        builder.append(", targetTablePartten=");
        builder.append(targetTablePartten);
        builder.append("]");
        return builder.toString();
    }
}
