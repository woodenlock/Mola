package com.resintec.mola.business.model;

import java.io.Serializable;

import com.resintec.mola.business.ResponseEnum;

/**
 * common response entity for web request
 * 
 * @author woodenlock
 *
 */
public class ResponseVO<T> implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * signal code for response
     */
    private Integer code;

    /**
     * description for response
     */
    private String message;

    /**
     * response data
     */
    T data;
    
    public ResponseVO(){
        this(ResponseEnum.SUCCESS);
    }
    
    public ResponseVO(T data){
        this(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data);
    }
    
    public ResponseVO(ResponseEnum en){
        this(null == en ? null : en.getCode(), null == en ? null : en.getMessage());
    }
    
    public ResponseVO(Integer code, String message){
        this(code, message, null);
    }
    
    public ResponseVO(Integer code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }
    
    /**
     * fast success
     */
    public void buildSuccess(){
        buildSuccess(null);
    }
    
    /**
     * fast success
     */
    public void buildSuccess(T data){
        this.code = ResponseEnum.SUCCESS.getCode();
        this.message = ResponseEnum.SUCCESS.getMessage();
        this.data = data;
    }
    
    /**
     * fast fail
     */
    public void buildFail(){
        this.code = ResponseEnum.FAILED.getCode();
        this.message = ResponseEnum.FAILED.getMessage();
    }
    
    /**
     * fast fail
     */
    public void buildFail(String message){
        this.code = ResponseEnum.FAILED.getCode();
        this.message = message;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ResponseVO [code=");
        builder.append(code);
        builder.append(", message=");
        builder.append(message);
        builder.append(", data=");
        builder.append(data);
        builder.append("]");
        return builder.toString();
    }
}