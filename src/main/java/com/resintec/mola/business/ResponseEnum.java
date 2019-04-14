package com.resintec.mola.business;

/**
 * all response statuses put here
 * @author woodenlock
 * @description 
 */
public enum ResponseEnum{
    /** success **/
    SUCCESS(00000000, "success"),
    
    /** failed **/
    FAILED(99999999, "failed");
    
    /**
     * response code(00000000~99999999)
     */
    private Integer code;

    /**
     * response description
     */
    private String message;
    
    private ResponseEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    
    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * get by code
     * @param code
     * @return
     */
    public static ResponseEnum getByCode(Integer code){
        for (ResponseEnum en : ResponseEnum.values()) {
            if(en.getCode().equals(code)){
                return en;
            }
        }
        return null;
    }
}
