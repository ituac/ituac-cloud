package org.ituac.upms.controller;

import org.apache.commons.lang.StringUtils;
import org.ituac.upms.entity.User;
import org.ituac.upms.service.UserDao;
import org.ituac.upms.service.UserLoginParamDto;
import org.ituac.upms.service.UserService;
import org.ituac.upms.utils.BPwdEncoderUtil;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    @Autowired
    private OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails;

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/login")
    public ResponseEntity<?> login(@Valid UserLoginParamDto loginDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors())
            throw new Exception("登录信息错误，请确认后再试");

        User user = userRepository.findByUsername(loginDto.getUsername());

        if (null == user)
            throw new Exception("用户为空，出错了");

        if (!BPwdEncoderUtil.matches(loginDto.getPassword(), user.getPassword().replace("{bcrypt}","")))
            throw new Exception("密码不正确");

        if (user.getUsername().equals("root_1")){
            throw new Exception("用户已经锁定");
        }



        String client_secret = oAuth2ClientProperties.getClientId()+":"+oAuth2ClientProperties.getClientSecret();

        client_secret = "Basic "+ Base64.getEncoder().encodeToString(client_secret.getBytes());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization",client_secret);

        //授权请求信息
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put("username", Collections.singletonList(loginDto.getUsername()));
        map.put("password", Collections.singletonList(loginDto.getPassword()));
        map.put("grant_type", Collections.singletonList(oAuth2ProtectedResourceDetails.getGrantType()));

        map.put("scope", oAuth2ProtectedResourceDetails.getScope());
        //HttpEntity
        HttpEntity httpEntity = new HttpEntity(map,httpHeaders);
        //获取 Token
        return restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST,httpEntity,OAuth2AccessToken.class);

    }


    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            logger.info("info:username:{} === password{}",username,password);
            userService.create(username,password);
        }

        return null;
    }

}