package com.spring.boot.sports.shiro;

/**
 * @author yuderen
 * @version 2018/9/13 18:18
 */
public class CodecException extends ShiroException {
    public CodecException() {
    }

    public CodecException(String message) {
        super(message);
    }

    public CodecException(Throwable cause) {
        super(cause);
    }

    public CodecException(String message, Throwable cause) {
        super(message, cause);
    }
}
