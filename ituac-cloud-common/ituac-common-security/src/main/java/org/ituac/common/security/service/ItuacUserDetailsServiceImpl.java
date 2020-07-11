package org.ituac.common.security.service;

import org.ituac.api.upms.feign.RemoteUserService;
import org.ituac.api.upms.model.dto.UserInfo;
import org.ituac.api.upms.model.entity.SysUser;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.common.kern.constant.CommonConstants;
import org.ituac.common.kern.constant.SecurityConstants;
import org.ituac.common.kern.util.R;
import org.springframework.security.core.userdetails.UserDetailsService;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * 用户详细信息
 *
 * @author boo
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class ItuacUserDetailsServiceImpl implements UserDetailsService {


    private final RemoteUserService remoteUserService;

    private final CacheManager cacheManager;

    /**
     * 用户密码登录
     * @param username 用户名
     * @return
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        Cache cache = cacheManager.getCache(CacheConstants.USER_DETAILS);
        //cache.clear();
        if (cache != null && cache.get(username) != null) {
            return (ItuacUser) cache.get(username).get();
        }

        R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
        UserDetails userDetails = getUserDetails(result);
        cache.put(username, userDetails);
        return userDetails;
    }

    /**
     * 构建userdetails
     * @param result 用户信息
     * @return
     */
    private UserDetails getUserDetails(R<UserInfo> result) {
        if (result == null || result.getData() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        UserInfo info = result.getData();
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getRoles())) {
            // 获取角色
            Arrays.stream(info.getRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));

        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SysUser user = info.getSysUser();

        // 构造security用户
        return new ItuacUser(user.getUserId(), user.getDeptId(), user.getUsername(),
                SecurityConstants.BCRYPT + user.getPassword(),
                StrUtil.equals(user.getLockFlag(), CommonConstants.STATUS_NORMAL), true, true, true, authorities);
    }



}
