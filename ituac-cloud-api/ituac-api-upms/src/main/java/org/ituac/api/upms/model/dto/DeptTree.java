package org.ituac.api.upms.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author boo
 * 部门树
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends TreeNode {

    private String name;

}