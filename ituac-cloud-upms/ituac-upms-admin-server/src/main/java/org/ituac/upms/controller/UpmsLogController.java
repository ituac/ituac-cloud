package org.ituac.upms.controller;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysLog;
import org.ituac.common.kern.util.R;
import org.ituac.common.security.annotation.Inner;
import org.ituac.upms.service.UpmsLogService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 日志表 前端控制器
 * @author boo
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
@Api(value = "log", tags = "日志管理模块")
public class UpmsLogController {

    private final UpmsLogService upmsLogService;

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param sysLog 系统日志
     * @return
     */
    @GetMapping("/page")
    public R getLogPage(Page page, SysLog sysLog) {
        return R.ok(upmsLogService.page(page, Wrappers.query(sysLog)));
    }

    /**
     * 删除日志
     * @param id ID
     * @return success/false
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_log_del')")
    public R removeById(@PathVariable Long id) {
        return R.ok(upmsLogService.removeById(id));
    }

    /**
     * 插入日志
     * @param sysLog 日志实体
     * @return success/false
     */
    @Inner
    @PostMapping
    public R save(@Valid @RequestBody SysLog sysLog) {
        return R.ok(upmsLogService.save(sysLog));
    }

}