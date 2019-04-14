package com.resintec.mola.model.config;

import java.io.Serializable;

import com.resintec.mola.enums.JdbcTypeEnum;

/**
 * configuration of the field detail need to deal with
 * @author woodenlock
 *
 */
public class FieldMappingConfiguration implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * name partten of the source catalog to change
     */
    private String sourceCatalogPartten;

    /**
     * name partten of the source Schame to change
     */
    private String sourceSchemaPartten;

    /**
     * name partten of the source table to change
     */
    private String sourceTablePartten;

    /**
     * name partten of the source column to change
     */
    private String sourceColumnNamePartten;

    /**
     * full type of the target column
     */
    private String sourceColumnFullType;

    /**
     * name of the target object
     */
    private String targetObjectName;

    /**
     * full type of the target object
     */
    private JdbcTypeEnum targetObjectFullType;
    
    public FieldMappingConfiguration(String sourceCatalogPartten, String sourceSchemaPartten, String sourceTablePartten,
        String sourceColumnNamePartten, String targetObjectName) {
        this.sourceCatalogPartten = sourceCatalogPartten;
        this.sourceSchemaPartten = sourceSchemaPartten;
        this.sourceTablePartten = sourceTablePartten;
        this.sourceColumnNamePartten = sourceColumnNamePartten;
        this.targetObjectName = targetObjectName;
    }
    
    public FieldMappingConfiguration(String sourceColumnFullType, JdbcTypeEnum targetObjectFullType) {
        this.sourceColumnFullType = sourceColumnFullType;
        this.targetObjectFullType = targetObjectFullType;
    }
    
    public FieldMappingConfiguration(String sourceCatalogPartten, String sourceSchemaPartten, String sourceTablePartten,
        String sourceColumnNamePartten, String sourceColumnFullType, String targetObjectName, JdbcTypeEnum targetObjectFullType) {
        this.sourceCatalogPartten = sourceCatalogPartten;
        this.sourceSchemaPartten = sourceSchemaPartten;
        this.sourceTablePartten = sourceTablePartten;
        this.sourceColumnNamePartten = sourceColumnNamePartten;
        this.sourceColumnFullType = sourceColumnFullType;
        this.targetObjectName = targetObjectName;
        this.targetObjectFullType = targetObjectFullType;
    }

    /**
     * @return the sourceColumnNamePartten
     */
    public String getSourceColumnNamePartten() {
        return sourceColumnNamePartten;
    }

    /**
     * @param sourceColumnNamePartten
     */
    public void setSourceColumnNamePartten(String sourceColumnNamePartten) {
        this.sourceColumnNamePartten = sourceColumnNamePartten;
    }

    /**
     * @return the sourceCatalogPartten
     */
    public String getSourceCatalogPartten() {
        return sourceCatalogPartten;
    }

    /**
     * @param sourceCatalogPartten
     */
    public void setSourceCatalogPartten(String sourceCatalogPartten) {
        this.sourceCatalogPartten = sourceCatalogPartten;
    }

    /**
     * @return the sourceSchamePartten
     */
    public String getSourceSchemaPartten() {
        return sourceSchemaPartten;
    }

    /**
     * @param sourceSchamePartten
     */
    public void setSourceSchemaPartten(String sourceSchamePartten) {
        this.sourceSchemaPartten = sourceSchamePartten;
    }

    /**
     * @return the sourceTablePartten
     */
    public String getSourceTablePartten() {
        return sourceTablePartten;
    }

    /**
     * @param sourceTablePartten
     */
    public void setSourceTablePartten(String sourceTablePartten) {
        this.sourceTablePartten = sourceTablePartten;
    }
    
    
    /**
     * @return the sourceColumnFullType
     */
    public String getSourceColumnFullType() {
        return sourceColumnFullType;
    }

    
    /**
     * @param sourceColumnFullType
     */
    public void setSourceColumnFullType(String sourceColumnFullType) {
        this.sourceColumnFullType = sourceColumnFullType;
    }

    /**
     * @return the targetObjectName
     */
    public String getTargetObjectName() {
        return targetObjectName;
    }

    /**
     * @param targetObjectName
     */
    public void setTargetObjectName(String targetObjectName) {
        this.targetObjectName = targetObjectName;
    }

    /**
     * @return the targetObjectFullType
     */
    public JdbcTypeEnum getTargetObjectFullType() {
        return targetObjectFullType;
    }

    /**
     * @param targetObjectFullType
     */
    public void setTargetObjectFullType(JdbcTypeEnum targetObjectFullType) {
        this.targetObjectFullType = targetObjectFullType;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FieldMappingConfiguration [sourceColumnNamePartten=");
        builder.append(sourceColumnNamePartten);
        builder.append(", sourceCatalogPartten=");
        builder.append(sourceCatalogPartten);
        builder.append(", sourceSchemaPartten=");
        builder.append(sourceSchemaPartten);
        builder.append(", sourceTablePartten=");
        builder.append(sourceTablePartten);
        builder.append(", sourceColumnFullType=");
        builder.append(sourceColumnFullType);
        builder.append(", targetObjectName=");
        builder.append(targetObjectName);
        builder.append(", targetObjectFullType=");
        builder.append(targetObjectFullType);
        builder.append("]");
        return builder.toString();
    }
}
