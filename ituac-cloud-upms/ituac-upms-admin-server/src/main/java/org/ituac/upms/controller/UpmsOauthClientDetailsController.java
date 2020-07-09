package org.ituac.upms.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysOauthClientDetails;
import org.ituac.common.kern.util.R;
import org.ituac.common.log.annotation.SysLog;
import org.ituac.upms.service.UpmsOauthClientDetailsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 前端控制器
 * @author boo
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Api(value = "client", tags = "客户端管理模块")
public class UpmsOauthClientDetailsController {

    private final UpmsOauthClientDetailsService upmsOauthClientDetailsService;

    /**
     * 通过ID查询
     * @param id clientId
     * @return SysOauthClientDetails
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable String id) {
        return R.ok(upmsOauthClientDetailsService.getById(id));
    }

    /**
     * 简单分页查询
     * @param page 分页对象
     * @param sysOauthClientDetails 系统终端
     * @return
     */
    @GetMapping("/page")
    public R getOauthClientDetailsPage(Page page, SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(upmsOauthClientDetailsService.page(page, Wrappers.query(sysOauthClientDetails)));
    }

    /**
     * 添加
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("添加终端")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_client_add')")
    public R add(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(upmsOauthClientDetailsService.save(sysOauthClientDetails));
    }

    /**
     * 删除
     * @param id ID
     * @return success/false
     */
    @SysLog("删除终端")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_client_del')")
    public R removeById(@PathVariable String id) {
        return R.ok(upmsOauthClientDetailsService.removeClientDetailsById(id));
    }

    /**
     * 编辑
     * @param sysOauthClientDetails 实体
     * @return success/false
     */
    @SysLog("编辑终端")
    @PutMapping
    @PreAuthorize("@pms.hasPermission('sys_client_edit')")
    public R update(@Valid @RequestBody SysOauthClientDetails sysOauthClientDetails) {
        return R.ok(upmsOauthClientDetailsService.updateClientDetailsById(sysOauthClientDetails));
    }

}