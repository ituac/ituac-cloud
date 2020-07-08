package org.ituac.api.upms.feign.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ituac.api.upms.feign.RemoteUserService;
import org.ituac.api.upms.model.dto.UserInfo;
import org.ituac.common.kern.util.R;
import org.springframework.stereotype.Component;

/**
 * @author boo
 */
@Slf4j
@Component
public class RemoteUserServiceFallbackImpl implements RemoteUserService {

    @Setter
    private Throwable cause;

    /**
     * 通过用户名查询用户、角色信息
     * @param username 用户名
     * @param from 内外标志
     * @return R
     */
    @Override
    public R<UserInfo> info(String username, String from) {
        log.error("feign 查询用户信息失败:{}", username, cause);
        return null;
    }

    /**
     * 通过社交账号查询用户、角色信息
     * @param inStr appid@code
     * @return
     */
    @Override
    public R<UserInfo> social(String inStr) {
        log.error("feign 查询用户信息失败:{}", inStr, cause);
        return null;
    }

}