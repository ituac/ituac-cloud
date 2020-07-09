package com.ituac.auth.handler;

import lombok.extern.slf4j.Slf4j;
import org.ituac.common.security.handler.AbstractAuthenticationFailureEvenHandler;
import org.ituac.common.security.handler.AbstractAuthenticationSuccessEventHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author ituac
 */

@Slf4j
@Component
public class AuthenticationSuccessEventHandler extends AbstractAuthenticationSuccessEventHandler {

    /**
     * 处理登录成功方法
     * 获取到登录的authentication 对象
     * @param authentication 登录对象
     */
    @Override
    public void handle(Authentication authentication) {
        log.info("用户：{} 登录成功", authentication.getPrincipal());
    }

}
