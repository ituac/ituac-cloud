package org.ituac.api.cms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 文章与类别关联表
 */
@Data
@TableName("cms_post_categories")
public class CmsPostCategories {
    private Integer id;
    private Timestamp create_time;
    private Integer deleted;
    private Timestamp update_time;
    private Integer category_id;
    private Integer post_id;
}
