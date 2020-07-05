package org.ituac.api.cms.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 *文章类别
 */
@TableName("cms_categories")
@Data
public class CmsCategories {
    private Integer id;
    private Timestamp create_time;
    private Integer deleted;
    private Timestamp update_time;
    private String description;
    private String name;
    private Integer parent_id;
    private String slug_name;
}
