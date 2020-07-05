package org.ituac.api.cms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 文章标签关联表
 */
@Data
@TableName("cms_post_tags")
public class CmsPostTags {
    private Integer id;
    private Timestamp create_time;
    private Integer deleted;
    private Timestamp update_time;
    private Integer post_id;
    private Integer tag_id;
}
