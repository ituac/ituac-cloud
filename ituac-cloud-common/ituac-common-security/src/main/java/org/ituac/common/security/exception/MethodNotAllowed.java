package org.ituac.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ituac.common.security.component.ItuacAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author boo
 * @date 2020
 */
@JsonSerialize(using = ItuacAuth2ExceptionSerializer.class)
public class MethodNotAllowed extends ItuacAuth2Exception {

    public MethodNotAllowed(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "method_not_allowed";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.METHOD_NOT_ALLOWED.value();
    }

}
