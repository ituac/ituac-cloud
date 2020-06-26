package org.ituac.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 安全配置
 * @ EnableWebSecurity 启用web安全
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * http安全配置
     * @param http http安全对象
     * @throws Exception http安全异常信息
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // 由于我们希望在用户界面中访问受保护的资源，因此我们需要允许创建会话
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                .and()
                .requestMatchers().anyRequest()
                .and()
                //启用匿名登录，不可访问受保护资源
                .anonymous()
                .and()
                .authorizeRequests()
                //配置protected访问控制，必须认证过后才可以访问
                .antMatchers("/uaa/oauth/**","/oauth/**","/user/registry/**", "/user/login/**").permitAll()
                .antMatchers("/**").authenticated();
    }
}
