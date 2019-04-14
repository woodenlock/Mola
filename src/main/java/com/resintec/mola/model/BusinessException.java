package com.resintec.mola.model;

/**
 * common business exception that don't need to print full stack
 * 
 * @author woodenlock
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@Override
    public Throwable fillInStackTrace() {
        return this;
    }
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
