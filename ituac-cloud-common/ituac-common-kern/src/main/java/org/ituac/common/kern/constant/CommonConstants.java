package org.ituac.common.kern.constant;

/**
 * @author ituac
 */
public interface CommonConstants {

    /**
     * 删除
     */
    String STATUS_DEL = "100";

    /**
     * 正常
     */
    String STATUS_NORMAL = "200";

    /**
     * 锁定
     */
    String STATUS_LOCK = "300";

    /**
     * 菜单树根节点
     */
    Integer MENU_TREE_ROOT_ID = -1;

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 前端工程名
     */
    String FRONT_END_PROJECT = "ituac-ui";

    /**
     * 后端工程名
     */
    String BACK_END_PROJECT = "ituac-cloud";

    /**
     * 成功标记
     */
    Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    Integer FAIL = -100;

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    /**
     * 当前页
     */
    String CURRENT = "current";

    /**
     * size
     */
    String SIZE = "size";

}