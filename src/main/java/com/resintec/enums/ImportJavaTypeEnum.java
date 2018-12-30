package com.resintec.enums;

import java.time.OffsetDateTime;
import java.util.Date;
import java.math.BigDecimal;

/**
 * java type not be included in default package
 * @author woodenlock
 *
 */
public enum ImportJavaTypeEnum {
	DATE("Date",Date.class.getName()),
	
	DECIMAL("BigDecimal",BigDecimal.class.getName()),
	
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
