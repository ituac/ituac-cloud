package org.ituac.api.cms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 文章标签表
 */
@Data
@TableName("cms_tags")
public class CmsTags {
    private Integer id;
    private Timestamp create_time;
    private Integer deleted;
    private Timestamp update_time;
    private String name;
    private String slug_name;
}
