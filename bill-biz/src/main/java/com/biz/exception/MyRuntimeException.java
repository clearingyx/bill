package com.biz.exception;


/**
 * RC unchecked 异常基类，可以存储RspCodeMsg
 * RuntimeException不是运行时异常，不用抛出，直接返回到前台
 * @author song.chang
 * @create 2015年8月19日
 */
public class MyRuntimeException extends RuntimeException {

    public MyRuntimeException() {
        super();
    }

    public MyRuntimeException(String message) {
        super(message);
    }

    public MyRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRuntimeException(Throwable cause) {
        super(cause);
    }

    protected MyRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
