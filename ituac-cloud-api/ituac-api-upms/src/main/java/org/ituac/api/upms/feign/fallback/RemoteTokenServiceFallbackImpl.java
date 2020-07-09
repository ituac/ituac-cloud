package org.ituac.api.upms.feign.fallback;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.ituac.api.upms.feign.RemoteTokenService;
import org.ituac.common.kern.util.R;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author boo
 */

@Slf4j
@Component
public class RemoteTokenServiceFallbackImpl implements RemoteTokenService {

    @Setter
    private Throwable cause;

    /**
     * 分页查询token 信息
     * @param params 分页参数
     * @param from 内部调用标志
     * @return page
     */
    @Override
    public R getTokenPage(Map<String, Object> params, String from) {
        log.error("调用认证中心查询token 失败", cause);
        return null;
    }

    /**
     * 删除token
     * @param s
     * @param id
     * @return
     */
    @Override
    public R<Boolean> removeToken(String s, String id) {
        log.error("删除token 失败 {}", id, cause);
        return null;
    }

}