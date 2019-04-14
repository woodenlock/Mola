package com.resintec.mola.model.db;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * info of the target table in database
 * 
 * @author woodenlock
 *
 */
public class Table implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * catalog
     */
    private String catalog;

    /**
     * schema name space in oracle
     */
    private String schema;

    /**
     * name
     */
    private String name;

    /**
     * name of the target java entity
     */
    private String entityName;

    /**
     * type
     */
    private String type;

    /**
     * remark
     */
    private String remarks;

    /**
     * basic columns it has
     */
    private List<Column> columns;

    /**
     * primary column of the table
     */
    private Column primaryColumn;

    /**
     * basic columns it has
     */
    private List<TableIndex> indexes;

    /**
     * extra type need to import when generate java file
     */
    private Map<String, String> imports;

    /**
     * @return the catalog
     */
    public String getCatalog() {
        return catalog;
    }

    /**
     * @param catalog
     */
    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    /**
     * @return the schema
     */
    public String getSchema() {
        return schema;
    }

    /**
     * @param schema
     */
    public void setSchema(String schema) {
        this.schema = schema;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the entityName
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * @param entityName
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * @param columns
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    /**
     * @return the primaryColumn
     */
    public Column getPrimaryColumn() {
        return primaryColumn;
    }

    /**
     * @param primaryColumn
     */
    public void setPrimaryColumn(Column primaryColumn) {
        this.primaryColumn = primaryColumn;
    }

    /**
     * @return the indexes
     */
    public List<TableIndex> getIndexes() {
        return indexes;
    }

    /**
     * @param indexes
     */
    public void setIndexes(List<TableIndex> indexes) {
        this.indexes = indexes;
    }

    public Map<String, String> getImports() {
        return imports;
    }

    public void setImports(Map<String, String> imports) {
        this.imports = imports;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Table [catalog=");
        builder.append(catalog);
        builder.append(", schema=");
        builder.append(schema);
        builder.append(", name=");
        builder.append(name);
        builder.append(", entityName=");
        builder.append(entityName);
        builder.append(", type=");
        builder.append(type);
        builder.append(", remarks=");
        builder.append(remarks);
        builder.append(", columns=");
        builder.append(columns);
        builder.append(", primaryColumn=");
        builder.append(primaryColumn);
        builder.append(", indexes=");
        builder.append(indexes);
        builder.append(", imports=");
        builder.append(imports);
        builder.append("]");
        return builder.toString();
    }
}
