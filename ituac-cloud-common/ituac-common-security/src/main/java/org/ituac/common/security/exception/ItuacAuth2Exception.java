package org.ituac.common.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.ituac.common.security.component.ItuacAuth2ExceptionSerializer;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;


/**
 * @author boo
 * @date 2020
 */
@JsonSerialize(using = ItuacAuth2ExceptionSerializer.class)
public class ItuacAuth2Exception extends OAuth2Exception {

    @Getter
    private String errorCode;

    public ItuacAuth2Exception(String msg) {
        super(msg);
    }

    public ItuacAuth2Exception(String msg, String errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }

}
