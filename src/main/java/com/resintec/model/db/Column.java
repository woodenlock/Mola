package com.resintec.model.db;

import java.io.Serializable;

/**
 * full description of the column from the database
 * 
 * @author woodenlock
 *
 */
public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * the catalog where the column in(may be null)
	 */
	private String catalog;

	/**
	 * the schema where the column in(may be null)
	 */
	private String schema;

	/**
	 * the table where the column in
	 */
	private String table;

	/**
	 * name
	 */
	private String name;

	/**
	 * SQL type from java.sql.Types
	 */
	private Integer dataType;

	/**
	 * name of the target entity prop
	 */
	private String javaName;

	/**
	 * type short name of the target entity prop
	 */
	private String javaTypeShortName;

	/**
	 * type full name of the target entity prop
	 */
	private String javaTypeFullName;

	/**
	 * Data source dependent type name, for a UDT the type name is fully qualified
	 */
	private String typeName;

	/**
	 * column size. For char or date types this is the maximum number of characters,
	 * for numeric or decimal types this is precision
	 */
	private Integer size;

	/**
	 * the number of fractional digits
	 */
	private Integer decimalDigits;

	/**
	 * Radix (typically either 10 or 2)
	 */
	private Integer numPercRadix;

	/**
	 * is NULL allowed. 1、columnNoNulls - might not allow NULL values
	 * 2、columnNullable - definitely allows NULL values 3、columnNullableUnknown -
	 * nullability unknown
	 */
	private Boolean nullable;

	/**
	 * wether is primary key
	 */
	private Boolean isPrimary;

	/**
	 * wether increase automatic
	 */
	private Boolean isAutoIncrement;

	/**
	 * wether generate automatic
	 */
	private Boolean isAutoGenerate;

	/**
	 * comment describing column (may be null)
	 */
	private String remarks;

	/**
	 * default value (may be null)
	 */
	private Object defaultValue;

	/**
	 * for char types the maximum number of bytes in the column
	 */
	private Integer charOctetLength;

	/**
	 * index of column in table (starting at 1)
	 */
	private Integer ordinalPosition;

	/**
	 * catalog of table that is the scope of a reference attribute (null if
	 * DATA_TYPE isn't REF)
	 */
	private String scopeCatalog;

	/**
	 * schema of table that is the scope of a reference attribute (null if the
	 * DATA_TYPE isn't REF)
	 */
	private String scopeSchema;

	/**
	 * table name that this the scope of a reference attribure (null if the
	 * DATA_TYPE isn't REF)
	 */
	private String scopeTable;

	/**
	 * source type of a distinct type or user-generated Ref type, SQL type from
	 * java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
	 */
	private Short sourceDataType;

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
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table
	 */
	public void setTable(String table) {
		this.table = table;
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
	 * @return the dataType
	 */
	public Integer getDataType() {
		return dataType;
	}

	/**
	 * @param dataType
	 */
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}

	/**
	 * @return the javaName
	 */
	public String getJavaName() {
		return javaName;
	}

	/**
	 * @param javaName
	 */
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}

	public String getJavaTypeShortName() {
		return javaTypeShortName;
	}

	public void setJavaTypeShortName(String javaTypeShortName) {
		this.javaTypeShortName = javaTypeShortName;
	}

	public String getJavaTypeFullName() {
		return javaTypeFullName;
	}

	public void setJavaTypeFullName(String javaTypeFullName) {
		this.javaTypeFullName = javaTypeFullName;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(Integer size) {
		this.size = size;
	}

	/**
	 * @return the decimalDigits
	 */
	public Integer getDecimalDigits() {
		return decimalDigits;
	}

	/**
	 * @param decimalDigits
	 */
	public void setDecimalDigits(Integer decimalDigits) {
		this.decimalDigits = decimalDigits;
	}

	/**
	 * @return the numPercRadix
	 */
	public Integer getNumPercRadix() {
		return numPercRadix;
	}

	/**
	 * @param numPercRadix
	 */
	public void setNumPercRadix(Integer numPercRadix) {
		this.numPercRadix = numPercRadix;
	}

	/**
	 * @return the nullable
	 */
	public Boolean getNullable() {
		return nullable;
	}

	/**
	 * @param nullable
	 */
	public void setNullable(Boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * @return the isPrimary
	 */
	public Boolean getIsPrimary() {
		return isPrimary;
	}

	/**
	 * @param isPrimary
	 */
	public void setIsPrimary(Boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	/**
	 * @return the isAutoIncrement
	 */
	public Boolean getIsAutoIncrement() {
		return isAutoIncrement;
	}

	/**
	 * @param isAutoIncrement
	 */
	public void setIsAutoIncrement(Boolean isAutoIncrement) {
		this.isAutoIncrement = isAutoIncrement;
	}

	/**
	 * @return the isAutoGenerate
	 */
	public Boolean getIsAutoGenerate() {
		return isAutoGenerate;
	}

	/**
	 * @param isAutoGenerate
	 */
	public void setIsAutoGenerate(Boolean isAutoGenerate) {
		this.isAutoGenerate = isAutoGenerate;
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
	 * @return the defaultValue
	 */
	public Object getDefaultValue() {
		return defaultValue;
	}

	/**
	 * @param defaultValue
	 */
	public void setDefaultValue(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * @return the charOctetLength
	 */
	public Integer getCharOctetLength() {
		return charOctetLength;
	}

	/**
	 * @param charOctetLength
	 */
	public void setCharOctetLength(Integer charOctetLength) {
		this.charOctetLength = charOctetLength;
	}

	/**
	 * @return the ordinalPosition
	 */
	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	/**
	 * @param ordinalPosition
	 */
	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	/**
	 * @return the scopeCatalog
	 */
	public String getScopeCatalog() {
		return scopeCatalog;
	}

	/**
	 * @param scopeCatalog
	 */
	public void setScopeCatalog(String scopeCatalog) {
		this.scopeCatalog = scopeCatalog;
	}

	/**
	 * @return the scopeSchema
	 */
	public String getScopeSchema() {
		return scopeSchema;
	}

	/**
	 * @param scopeSchema
	 */
	public void setScopeSchema(String scopeSchema) {
		this.scopeSchema = scopeSchema;
	}

	/**
	 * @return the scopeTable
	 */
	public String getScopeTable() {
		return scopeTable;
	}

	/**
	 * @param scopeTable
	 */
	public void setScopeTable(String scopeTable) {
		this.scopeTable = scopeTable;
	}

	/**
	 * @return the sourceDataType
	 */
	public Short getSourceDataType() {
		return sourceDataType;
	}

	/**
	 * @param sourceDataType
	 */
	public void setSourceDataType(Short sourceDataType) {
		this.sourceDataType = sourceDataType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Column [catalog=");
		builder.append(catalog);
		builder.append(", schema=");
		builder.append(schema);
		builder.append(", table=");
		builder.append(table);
		builder.append(", name=");
		builder.append(name);
		builder.append(", dataType=");
		builder.append(dataType);
		builder.append(", typeName=");
		builder.append(typeName);
		builder.append(", javaName=");
		builder.append(javaName);
		builder.append(", javaTypeShortName=");
		builder.append(javaTypeShortName);
		builder.append(", javaTypeFullName=");
		builder.append(javaTypeFullName);
		builder.append(", size=");
		builder.append(size);
		builder.append(", decimalDigits=");
		builder.append(decimalDigits);
		builder.append(", numPercRadix=");
		builder.append(numPercRadix);
		builder.append(", nullable=");
		builder.append(nullable);
		builder.append(", isPrimary=");
		builder.append(isPrimary);
		builder.append(", isAutoIncrement=");
		builder.append(isAutoIncrement);
		builder.append(", isAutoGenerate=");
		builder.append(isAutoGenerate);
		builder.append(", remarks=");
		builder.append(remarks);
		builder.append(", defaultValue=");
		builder.append(defaultValue);
		builder.append(", charOctetLength=");
		builder.append(charOctetLength);
		builder.append(", ordinalPosition=");
		builder.append(ordinalPosition);
		builder.append(", scopeCatalog=");
		builder.append(scopeCatalog);
		builder.append(", scopeSchema=");
		builder.append(scopeSchema);
		builder.append(", scopeTable=");
		builder.append(scopeTable);
		builder.append(", sourceDataType=");
		builder.append(sourceDataType);
		builder.append("]");
		return builder.toString();
	}
}
