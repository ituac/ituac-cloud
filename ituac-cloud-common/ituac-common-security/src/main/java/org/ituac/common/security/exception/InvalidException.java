package org.ituac.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ituac.common.security.component.ItuacAuth2ExceptionSerializer;

@JsonSerialize(using = ItuacAuth2ExceptionSerializer.class)
public class InvalidException extends ItuacAuth2Exception{

    public InvalidException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_exception";
    }

    @Override
    public int getHttpErrorCode() {
        return 426;
    }

}
