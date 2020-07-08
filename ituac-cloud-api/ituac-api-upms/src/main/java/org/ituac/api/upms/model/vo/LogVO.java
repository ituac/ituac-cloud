package org.ituac.api.upms.model.vo;

import lombok.Data;
import org.ituac.api.upms.model.entity.SysLog;

import java.io.Serializable;

/**
 * @author boo
 */
@Data
public class LogVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private SysLog sysLog;

    private String username;

}
