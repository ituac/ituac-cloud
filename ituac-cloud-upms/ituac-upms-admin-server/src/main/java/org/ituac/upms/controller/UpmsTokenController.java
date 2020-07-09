package org.ituac.upms.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.feign.RemoteTokenService;
import org.ituac.common.kern.constant.SecurityConstants;
import org.ituac.common.kern.util.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author boo
 * getTokenPage 管理
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
@Api(value = "token", tags = "令牌管理模块")
public class UpmsTokenController {

    private final RemoteTokenService remoteTokenService;

    /**
     * 分页token 信息
     * @param params 参数集
     * @return token集合
     */
    @GetMapping("/page")
    public R token(@RequestParam Map<String, Object> params) {
        return remoteTokenService.getTokenPage(params, SecurityConstants.FROM_IN);
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_token_del')")
    public R<Boolean> delete(@PathVariable String id) {
        return remoteTokenService.removeToken(id, SecurityConstants.FROM_IN);
    }

}