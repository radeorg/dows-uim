package org.dows.uim.exception;

import org.dows.rade.exception.RadeException;
import org.dows.rade.status.CommonStatusCode;
import org.dows.rade.status.StatusCode;

public class UimException extends RadeException {

    public UimException() {
    }

    public UimException(String msg) {
        super(Integer.valueOf(CommonStatusCode.FAILED.getCode()), msg);
    }

    public UimException(Integer code, String msg) {
        super(msg);
    }

    public UimException(Integer code, String msg, Throwable e) {
        super(msg, e);
    }

    public UimException(Object data) {
        super(data);
    }

    public UimException(Throwable throwable) {
        super(throwable);
    }

    public UimException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UimException(StatusCode statusCode) {
        super(statusCode.getDescribe());
        this.statusCode = statusCode;
    }

    public UimException(StatusCode statusCode, Exception exception) {
        super(String.format(statusCode.getDescribe(), exception.getMessage()));
        this.statusCode = statusCode;
    }

    public UimException(StatusCode statusCode, String msg) {
        super(String.format(statusCode.getDescribe(), msg));
        this.statusCode = statusCode;
    }

    public UimException(StatusCode statusCode, Object[] args, String message) {
        super(message);
        this.statusCode = statusCode;
        this.args = args;
    }

    public UimException(StatusCode statusCode, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
        this.args = args;
    }
}
