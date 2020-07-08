package org.ituac.upms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ituac.api.upms.model.entity.SysLog;
import org.ituac.upms.mapper.UpmsLogMapper;
import org.ituac.upms.service.UpmsLogService;
import org.springframework.stereotype.Service;

/**
 * 日志表 服务实现类
 *
 * @author boo
 */

@Service
public class UpmsLogServiceImpl extends ServiceImpl<UpmsLogMapper, SysLog> implements UpmsLogService {

}
