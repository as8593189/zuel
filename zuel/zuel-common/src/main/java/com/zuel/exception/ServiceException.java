package com.zuel.exception;

/*
 * 
 * @author:汪思超
 * @service:provider服务异常信息
 * @date:2020.12.6
 * */

@SuppressWarnings("serial")
public class ServiceException extends Exception{

	public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException() {
    }
}
