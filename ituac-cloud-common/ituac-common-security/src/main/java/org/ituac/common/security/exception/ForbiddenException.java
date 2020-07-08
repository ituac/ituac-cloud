package org.ituac.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ituac.common.security.component.ItuacAuth2ExceptionSerializer;
import org.springframework.http.HttpStatus;

/**
 * @author boo
 * @date 2020
 */
@JsonSerialize(using = ItuacAuth2ExceptionSerializer.class)
public class ForbiddenException extends ItuacAuth2Exception {

    public ForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "access_denied";
    }

    @Override
    public int getHttpErrorCode() {
        return HttpStatus.FORBIDDEN.value();
    }


}
