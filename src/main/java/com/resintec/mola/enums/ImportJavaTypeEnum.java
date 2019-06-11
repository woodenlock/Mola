package com.resintec.mola.enums;

import java.time.OffsetDateTime;
import java.util.Date;
import java.math.BigDecimal;

/**
 * java type not be included in default package
 * @author woodenlock
 *
 */
public enum ImportJavaTypeEnum {
	/** Date **/
	DATE("Date",Date.class.getName()),
	
	/** BigDecimal **/
	DECIMAL("BigDecimal",BigDecimal.class.getName()),
	
	/** OffsetDateTime **/
	OFFSET("OffsetDateTime",OffsetDateTime.class.getName());
	
	/** short name of java type **/
	private String shortName;
	
	/** full name of java type **/
	private String fullName;
	
	ImportJavaTypeEnum(String shortName, String fullName){
		this.shortName = shortName;
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}
}
