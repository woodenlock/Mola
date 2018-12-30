package com.resintec.enums;

import java.util.Date;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * jdbc enum(mysql)
 * @see com.mysql.cj.jdbc.result.com.mysql.cj.jdbc.result.ResultSetImpl.getObject(int columnIndex)
 * @author woodenlock
 */
public enum JdbcTypeEnum {
    /** longn varchar **/
	LONGNVARCHAR("LONGNVARCHAR", "String", String.class.getName(), -16),
	
	/** nchar **/
	NCHAR("NCHAR", "String", String.class.getName(), -15),
	
	/** nvarchar **/
	NVARCHAR("NVARCHAR", "String", String.class.getName(), -9),
	
	/** bit **/
	BIT("BIT", "Boolean", Boolean.class.getName(), -7),
	
	/** tiny int **/
	TINYINT("TINYINT", "Integer", Integer.class.getName(), -6),
	
	/** big int **/
	BIGINT("BIGINT", "Long", Long.class.getName(), -5),
	
	/** long varbinary **/
	LONGVARBINARY("LONGVARBINARY", "byte[]", "byte[]", -4),
	
	/** varbinary **/
	VARBINARY("VARBINARY", "byte[]", "byte[]", -3),
	
	/** binary **/
	BINARY("BINARY", "byte[]", "byte[]", -2),
	
	/** text **/
	TEXT("TEXT", "String", String.class.getName(), -1),
	
	/** null **/
	NULL("NULL", "Object", Object.class.getName(), 0),
	
	/** char **/
	CHAR("CHAR", "String", String.class.getName(), 1),
	
	/** numeric **/
	NUMERIC("NUMERIC", "BigDecimal", BigDecimal.class.getName(), 2),
	
	/** decimal **/
	DECIMAL("DECIMAL", "BigDecimal", BigDecimal.class.getName(), 3),
	
	/** integer **/
	INTEGER("INTEGER", "Integer", Integer.class.getName(), 4),
	
	/** small int **/
	SMALLINT("SMALLINT", "Short", Short.class.getName(), 5),
	
	/** float **/
	FLOAT("FLOAT", "Double", Double.class.getName(), 6),
	
	/** real **/
	REAL("REAL", "Float", Float.class.getName(), 7),
	
	/** double **/
	DOUBLE("DOUBLE", "Double", Double.class.getName(), 8),
	
	/** varchar **/
	VARCHAR("VARCHAR", "String", String.class.getName(), 12),
	
	/** boolean **/
	BOOLEAN("BOOLEAN", "Boolean", Boolean.class.getName(), 16),
	
	/** data link **/
	DATALINK("DATALINK", "Object", Object.class.getName(), 70),
	
	/** date **/
	DATE("DATE", "Date", Date.class.getName(), 91),
	
	/** time **/
	TIME("TIME", "Date", Date.class.getName(), 92),
	
	/** timestamp **/
	TIMESTAMP("TIMESTAMP", "Date", Date.class.getName(), 93),
	
	/** other **/
	OTHER("OTHER", "Object", Object.class.getName(), 1111),
	
	/** java object **/
	JAVA_OBJECT("JAVA_OBJECT", "Object", Object.class.getName(), 2000),
	
	/** distinct **/
	DISTINCT("DISTINCT", "Object", Object.class.getName(), 2001),
	
	/** struct **/
	STRUCT("STRUCT", "Object", Object.class.getName(), 2002),
	
	/** array **/
	ARRAY("ARRAY", "Object", Object.class.getName(), 2003),
	
	/** blob **/
	BLOB("BLOB", "byte[]", "byte[]", 2004),
	
	/** clob **/
	CLOB("CLOB", "String", String.class.getName(), 2005),
	
	/** ref **/
	REF("REF", "Object", Object.class.getName(), 2006),
	
	/** nclob **/
	NCLOB("NCLOB", "String", String.class.getName(), 2011),
	
	/** time with timezone **/
	TIME_WITH_TIMEZONE("TIME_WITH_TIMEZONE", "Date", OffsetDateTime.class.getName(), 2013),
	
	/** timestamp with timezone **/
	TIMESTAMP_WITH_TIMEZONE("TIMESTAMP_WITH_TIMEZONE", "Date", OffsetDateTime.class.getName(), 2014);
	//TODO more types can be defined here
	
	/**
	 * name of the jdbc type
	 */
	private final String jdbcName;
	
	/**
	 * short name of the java type
	 */
	private final String javaShortName;
	
	/**
	 * name of the mapping java full name
	 */
	private String javaFullName;
	
	/**
	 * code of the jdbc type
	 */
	private final Integer jdbcCode;
	
	private JdbcTypeEnum(String jdbcName, String javaShortName, String javaFullName, Integer jdbcCode) {
		this.jdbcName = jdbcName;
		this.javaShortName = javaShortName;
		this.javaFullName = javaFullName;
		this.jdbcCode = jdbcCode;
	}

	public String getJdbcName() {
		return jdbcName;
	}

	public String getJavaFullName() {
		return javaFullName;
	}
	
	public String getJavaShortName() {
		return javaShortName;
	}
	
	public Integer getJdbcCode() {
	    return jdbcCode;
	}
	
	/**
	 * get by the code of jdbc type
	 * @param jdbcName
	 * @return
	 */
	public static JdbcTypeEnum getByJdbcCode(Integer code) {
	    if(null == code){
	        throw new IllegalArgumentException("Failed to look for JdbcTypeEnum by jdbc code due to empty param:code.");
	    }
		
	    for (JdbcTypeEnum en : JdbcTypeEnum.values()) {
            if(en.getJdbcCode().equals(code)){
                return en;
            }
        }
	    
	    return null;
	}
}
