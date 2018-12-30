package com.resintec.model.db;

import java.io.Serializable;

/**
 * index of a table in database
 * 
 * @author woodenlock
 *
 */
public class TableIndex implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * name
     * null if tableIndexStatistic
     */
    private String name;

    /**
     * index type:
     * 1、tableIndexStatistic - this identifies table statistics that are returned in conjuction with a table's index descriptions
     * 2、tableIndexClustered - this is a clustered index
     * 3、tableIndexHashed - this is a hashed index
     * 4、tableIndexOther - this is some other style of index
     */
    private Short type;

    /**
     * wether is forced to be unique
     * false if tableIndexStatistic
     */
    private Boolean forceUnique;

    /**
     * qualifier
     * null if tableIndexStatistic
     */
    private String qualifier;

    /**
     * position
     * 0 if tableIndexStatistic
     */
    private Short position;

    /**
     * columnName
     * null if tableIndexStatistic
     */
    private String columnName;

    /**
     * order
     * A: ASC;B:DESC;null if tableIndexStatistic
     */
    private String order;

    /**
     * order
     * count of the column in the table if tableIndexStatistic,else count of the unique index
     */
    private Integer cardinality;

    /**
     * order
     * page number of the table if tableIndexStatistic,else page number of the index
     */
    private Integer pages;

    /**
     * filter
     * could be null
     */
    private String filter;

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
     * @return the type
     */
    public Short getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Short type) {
        this.type = type;
    }

    /**
     * @return the forceUnique
     */
    public Boolean getForceUnique() {
        return forceUnique;
    }

    /**
     * @param forceUnique
     */
    public void setForceUnique(Boolean forceUnique) {
        this.forceUnique = forceUnique;
    }

    /**
     * @return the qualifier
     */
    public String getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier
     */
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return the position
     */
    public Short getPosition() {
        return position;
    }

    /**
     * @param position
     */
    public void setPosition(Short position) {
        this.position = position;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the order
     */
    public String getOrder() {
        return order;
    }

    /**
     * @param order
     */
    public void setOrder(String order) {
        this.order = order;
    }

    /**
     * @return the cardinality
     */
    public Integer getCardinality() {
        return cardinality;
    }

    /**
     * @param cardinality
     */
    public void setCardinality(Integer cardinality) {
        this.cardinality = cardinality;
    }

    /**
     * @return the pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * @param pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * @return the filter
     */
    public String getFilter() {
        return filter;
    }

    /**
     * @param filter
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TableIndex [name=");
        builder.append(name);
        builder.append(", type=");
        builder.append(type);
        builder.append(", forceUnique=");
        builder.append(forceUnique);
        builder.append(", qualifier=");
        builder.append(qualifier);
        builder.append(", position=");
        builder.append(position);
        builder.append(", columnName=");
        builder.append(columnName);
        builder.append(", order=");
        builder.append(order);
        builder.append(", cardinality=");
        builder.append(cardinality);
        builder.append(", pages=");
        builder.append(pages);
        builder.append(", filter=");
        builder.append(filter);
        builder.append("]");
        return builder.toString();
    }
}
