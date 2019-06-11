package com.resintec.mola.enums;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * jdbc enum(mysql)
 * 
 * @see com.mysql.cj.jdbc.result.com.mysql.cj.jdbc.result.ResultSetImpl.getObject(int
 *      columnIndex) ibatis alse org.apache.ibatis.type.JdbcType
 * @author woodenlock
 */
public enum JdbcTypeEnum {
	/** longn varchar **/
	LONGNVARCHAR("VARCHAR", "String", String.class.getName(), java.sql.Types.LONGNVARCHAR),

	/** nchar **/
	NCHAR("VARCHAR", "String", String.class.getName(), java.sql.Types.NCHAR),

	/** nvarchar **/
	NVARCHAR("VARCHAR", "String", String.class.getName(), java.sql.Types.NVARCHAR),

	/** bit **/
	BIT("BIT", "Boolean", Boolean.class.getName(), java.sql.Types.BIT),

	/** tiny int **/
	TINYINT("TINYINT", "Integer", Integer.class.getName(), java.sql.Types.TINYINT),

	/** big int **/
	BIGINT("BIGINT", "Long", Long.class.getName(), java.sql.Types.BIGINT),

	/** long varbinary **/
	LONGVARBINARY("VARCHAR", "byte[]", "byte[]", java.sql.Types.LONGVARBINARY),

	/** varbinary **/
	VARBINARY("VARCHAR", "byte[]", "byte[]", java.sql.Types.VARBINARY),

	/** binary **/
	BINARY("VARCHAR", "byte[]", "byte[]", java.sql.Types.BINARY),

	/** text **/
	TEXT("VARCHAR", "String", String.class.getName(), java.sql.Types.LONGVARCHAR),

	/** null **/
	NULL("NULL", "Object", Object.class.getName(), java.sql.Types.NULL),

	/** char **/
	CHAR("CHAR", "String", String.class.getName(), java.sql.Types.CHAR),

	/** numeric **/
	NUMERIC("NUMERIC", "BigDecimal", BigDecimal.class.getName(), java.sql.Types.NUMERIC),

	/** decimal **/
	DECIMAL("DECIMAL", "BigDecimal", BigDecimal.class.getName(), java.sql.Types.DECIMAL),

	/** integer **/
	INTEGER("INTEGER", "Integer", Integer.class.getName(), java.sql.Types.INTEGER),

	/** small int **/
	SMALLINT("SMALLINT", "Short", Short.class.getName(), java.sql.Types.SMALLINT),

	/** float **/
	FLOAT("FLOAT", "Double", Double.class.getName(), java.sql.Types.FLOAT),

	/** real **/
	REAL("REAL", "Float", Float.class.getName(), java.sql.Types.REAL),

	/** double **/
	DOUBLE("DOUBLE", "Double", Double.class.getName(), java.sql.Types.DOUBLE),

	/** varchar **/
	VARCHAR("VARCHAR", "String", String.class.getName(), java.sql.Types.VARCHAR),

	/** boolean **/
	BOOLEAN("BOOLEAN", "Boolean", Boolean.class.getName(), java.sql.Types.BOOLEAN),

	/** data link **/
	DATALINK("DATALINK", "Object", Object.class.getName(), java.sql.Types.DATALINK),

	/** date **/
	DATE("DATE", "Date", Date.class.getName(), java.sql.Types.DATE),

	/** time **/
	TIME("TIME", "Date", Date.class.getName(), java.sql.Types.TIME),

	/** timestamp **/
	TIMESTAMP("TIMESTAMP", "Date", Date.class.getName(), java.sql.Types.TIMESTAMP),

	/** other **/
	OTHER("OTHER", "Object", Object.class.getName(), java.sql.Types.OTHER),

	/** java object **/
	JAVA_OBJECT("JAVA_OBJECT", "Object", Object.class.getName(), java.sql.Types.JAVA_OBJECT),

	/** distinct **/
	DISTINCT("DISTINCT", "Object", Object.class.getName(), java.sql.Types.DISTINCT),

	/** struct **/
	STRUCT("STRUCT", "Object", Object.class.getName(), java.sql.Types.STRUCT),

	/** array **/
	ARRAY("ARRAY", "Object", Object.class.getName(), java.sql.Types.ARRAY),

	/** blob **/
	BLOB("BLOB", "byte[]", "byte[]", java.sql.Types.BLOB),

	/** clob **/
	CLOB("CLOB", "String", String.class.getName(), java.sql.Types.CLOB),

	/** ref **/
	REF("REF", "Object", Object.class.getName(), java.sql.Types.REF),

	/** nclob **/
	NCLOB("NCLOB", "String", String.class.getName(), java.sql.Types.NCLOB),

	/** time with timezone **/
	TIME_WITH_TIMEZONE("TIME_WITH_TIMEZONE", "Date", OffsetDateTime.class.getName(), java.sql.Types.TIME_WITH_TIMEZONE),

	/** timestamp with timezone **/
	TIMESTAMP_WITH_TIMEZONE("TIMESTAMP_WITH_TIMEZONE", "Date", OffsetDateTime.class.getName(),
			java.sql.Types.TIMESTAMP_WITH_TIMEZONE);
	// TODO more types can be defined here

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

	private static Map<Integer, JdbcTypeEnum> lookup = new HashMap<Integer, JdbcTypeEnum>(64);
	static {
		for (JdbcTypeEnum en : JdbcTypeEnum.values()) {
			lookup.put(en.getJdbcCode(), en);
		}
	};

	/**
	 * get by the code of jdbc type
	 * 
	 * @param jdbcName
	 * @return
	 */
	public static JdbcTypeEnum getByJdbcCode(Integer code) {
		return lookup.get(code);
	}
}