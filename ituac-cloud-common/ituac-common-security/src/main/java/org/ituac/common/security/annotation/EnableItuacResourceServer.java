package org.ituac.common.security.annotation;


import org.ituac.common.security.component.ItuacResourceServerAutoConfiguration;
import org.ituac.common.security.component.ItuacSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * @author boo
 * 资源服务注解
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ ItuacResourceServerAutoConfiguration.class, ItuacSecurityBeanDefinitionRegistrar.class })
public @interface EnableItuacResourceServer {






}
