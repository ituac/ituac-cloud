package org.ituac.api.cms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 文章表
 */
@Data
@TableName("cms_posts")
public class CmsPosts {
    private Integer type;
    private Integer id;
    private Timestamp create_time;
    private Integer deleted;
    private Timestamp update_time;
    private Integer create_from;
    private Integer disallow_comment;
    private Timestamp edit_time;
    private String format_content;
    private Long likes;
    private String original_content;
    private String password;
    private Integer status;
    private String summary;
    private String template;
    private String thumbnail;
    private String title;
    private Integer top_priority;
    private String url;
    private Long visits;
    private Integer site_id;
}
