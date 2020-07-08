package org.ituac.common.kern.exception;

import lombok.NoArgsConstructor;

/**
 * @author ituac
 */

@NoArgsConstructor
public class ItuacRefuseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ItuacRefuseException(String message) {
        super(message);
    }

    public ItuacRefuseException(Throwable cause) {
        super(cause);
    }

    public ItuacRefuseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItuacRefuseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
