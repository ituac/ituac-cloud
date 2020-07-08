package org.ituac.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ituac.api.upms.model.entity.SysOauthClientDetails;

/**
 * @author ituac
 */


public interface UpmsOauthClientDetailsService extends IService<SysOauthClientDetails> {

        /**
         * 通过ID删除客户端
         * @param id
         * @return
         */
        Boolean removeClientDetailsById(String id);

        /**
         * 根据客户端信息
         * @param sysOauthClientDetails
         * @return
         */
        Boolean updateClientDetailsById(SysOauthClientDetails sysOauthClientDetails);

}