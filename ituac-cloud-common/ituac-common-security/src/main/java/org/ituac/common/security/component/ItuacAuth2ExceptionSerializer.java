package org.ituac.common.security.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;
import org.ituac.common.kern.constant.CommonConstants;
import org.ituac.common.security.exception.ItuacAuth2Exception;

/**
 * @author boo
 * @date 2020
 * OAuth2 异常格式化
 */

public class ItuacAuth2ExceptionSerializer extends StdSerializer<ItuacAuth2Exception> {


    public ItuacAuth2ExceptionSerializer() {
        super(ItuacAuth2Exception.class);
    }

    @Override
    @SneakyThrows
    public void serialize(ItuacAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject();
        gen.writeObjectField("code", CommonConstants.FAIL);
        gen.writeStringField("msg", value.getMessage());
        gen.writeStringField("data", value.getErrorCode());
        gen.writeEndObject();
    }

}
