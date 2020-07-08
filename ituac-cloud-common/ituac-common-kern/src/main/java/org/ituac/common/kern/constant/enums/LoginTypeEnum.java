package org.ituac.common.kern.constant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author boo
 */
@Getter
@RequiredArgsConstructor
public enum LoginTypeEnum {

    /**
     * 账号密码登录
     */
    PWD("IPWD", "账号密码登录"),

    /**
     * QQ登录
     */
    QQ("IQQ", "QQ登录"),

    /**
     * 微信登录
     */
    WECHAT("IWX", "微信登录");

    /**
     * 类型
     */
    private final String type;

    /**
     * 描述
     */
    private final String description;

}