package org.ituac.upms.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.ituac.api.upms.model.common.Value;
import org.ituac.api.upms.model.dto.UserDTO;
import org.ituac.api.upms.model.entity.SysUser;
import org.ituac.api.upms.model.entity.SysUsers;
import org.ituac.common.kern.util.R;
import org.ituac.common.log.annotation.SysLog;
import org.ituac.common.security.annotation.Inner;
import org.ituac.common.security.utils.SecurityUtils;
import org.ituac.upms.mapper.UpmsUserMapper;
import org.ituac.api.upms.model.dto.UserLoginParamDto;
import org.ituac.upms.service.UpmsUserService;
import org.ituac.upms.utils.BPwdEncoderUtil;
import org.springframework.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import javax.validation.Valid;
import java.util.Base64;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Api(value = "user", tags = "用户管理模块")
public class UpmsUserController {

    Logger logger = LoggerFactory.getLogger(UpmsUserController.class);

    @Autowired
    private UpmsUserService upmsUserService;

    //@Autowired
    //private UpmsUserMapper userRepository;

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


    /**
     *
     * @param loginDto
     * @param bindingResult
     * @return
     * @throws Exception
     *//*

    @RequestMapping("/login")
    public ResponseEntity<Value> login(@Valid UserLoginParamDto loginDto, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors())
            throw new Exception("登录信息错误，请确认后再试");
        SysUsers user = null;
        //user = userRepository.findByUsername(loginDto.getUsername());

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

        //登录成功 修改用户登录状态
        upmsUserService.updateLoginStatusWithOnLine(user);

        //HttpEntity
        HttpEntity httpEntity = new HttpEntity(map,httpHeaders);
        //获取 Token
        return ResponseEntity.ok(Value.getSuccess(restTemplate.exchange(oAuth2ProtectedResourceDetails.getAccessTokenUri(), HttpMethod.POST,httpEntity,OAuth2AccessToken.class)));

    }


    */
/**
     * 注册
     * @param username
     * @param password
     * @return
     *//*

    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public ResponseEntity<Value> createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        SysUsers user = new SysUsers();
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            logger.info("info:username:{} === password{}",username,password);
            SysUsers users = null;
            //SysUsers users = userRepository.findByUsername(username);
            if(users == null){
                user = upmsUserService.create(username,password);
            }else{
                return ResponseEntity.ok(Value.getFailure("该用户已存在"));
            }
        }
        return ResponseEntity.ok(Value.getSuccess(user));
    }
*/

    /**
     *
     * @param username  退出（未完成）
     * @return
     */
    /*@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<Value> logout(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            logger.info("info:username:{} === password{}",username,password);
            upmsUserService.logintOut(username);
        }
        return ResponseEntity.ok(Value.getSuccess("成功退出登录"));
    }*/


    /**
     * 获取当前用户全部信息
     * @return 用户信息
     */
    @GetMapping(value = { "/info" })
    public R info() {
        String username = SecurityUtils.getUser().getUsername();
        SysUser user = upmsUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.failed("获取当前用户信息失败");
        }
        return R.ok(upmsUserService.getUserInfo(user));
    }

    /**
     * 获取指定用户全部信息
	 * @return 用户信息
	 */
    @Inner
    @GetMapping("/info/{username}")
    public R info(@PathVariable String username) {
        SysUser user = upmsUserService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.failed(String.format("用户信息为空 %s", username));
        }
        return R.ok(upmsUserService.getUserInfo(user));
    }

    /**
     * 通过ID查询用户信息
     * @param id ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public R user(@PathVariable Integer id) {
        return R.ok(upmsUserService.getUserVoById(id));
    }

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    @GetMapping("/details/{username}")
    public R user(@PathVariable String username) {
        SysUser condition = new SysUser();
        condition.setUsername(username);
        return R.ok(upmsUserService.getOne(new QueryWrapper<>(condition)));
    }

    /**
     * 删除用户信息
     * @param id ID
     * @return R
     */
    @SysLog("删除用户信息")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_user_del')")
    public R userDel(@PathVariable Integer id) {
        SysUser sysUser = upmsUserService.getById(id);
        return R.ok(upmsUserService.removeUserById(sysUser));
    }

    /**
     * 添加用户
     * @param userDto 用户信息
     * @return success/false
     */
    @SysLog("添加用户")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_user_add')")
    public R user(@RequestBody UserDTO userDto) {
        return R.ok(upmsUserService.saveUser(userDto));
    }

    /**
     * 更新用户信息
     * @param userDto 用户信息
     * @return R
     */
    @SysLog("更新用户信息")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_user_edit')")
    public R updateUser(@Valid @RequestBody UserDTO userDto) {
        return R.ok(upmsUserService.updateUser(userDto));
    }

    /**
     * 分页查询用户
     * @param page 参数集
     * @param userDTO 查询参数列表
     * @return 用户集合
     */
    @GetMapping("/page")
    public R getUserPage(Page page, UserDTO userDTO) {
        return R.ok(upmsUserService.getUserWithRolePage(page, userDTO));
    }

    /**
     * 修改个人信息
     * @param userDto userDto
     * @return success/false
     */
    @SysLog("修改个人信息")
    @PutMapping("/edit")
    public R updateUserInfo(@Valid @RequestBody UserDTO userDto) {
        return upmsUserService.updateUserInfo(userDto);
    }

    /**
     * @param username 用户名称
     * @return 上级部门用户列表
     */
    @GetMapping("/ancestor/{username}")
    public R listAncestorUsers(@PathVariable String username) {
        return R.ok(upmsUserService.listAncestorUsersByUsername(username));
    }


}