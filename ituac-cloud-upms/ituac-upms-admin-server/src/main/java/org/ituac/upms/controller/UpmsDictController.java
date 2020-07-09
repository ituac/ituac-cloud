package org.ituac.upms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.upms.model.entity.SysDict;
import org.ituac.api.upms.model.entity.SysDictItem;
import org.ituac.common.kern.constant.CacheConstants;
import org.ituac.common.kern.util.R;
import org.ituac.common.log.annotation.SysLog;
import org.ituac.upms.service.UpmsDictItemService;
import org.ituac.upms.service.UpmsDictService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 字典表 前端控制器
 * @author boo
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/dict")
@Api(value = "dict", tags = "字典管理模块")
public class DictController {

    private final UpmsDictItemService upmsDictItemService;

    private final UpmsDictService upmsDictService;

    /**
     * 通过ID查询字典信息
     * @param id ID
     * @return 字典信息
     */
    @GetMapping("/{id}")
    public R getById(@PathVariable Integer id) {
        return R.ok(upmsDictService.getById(id));
    }

    /**
     * 分页查询字典信息
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("/page")
    public R<IPage> getDictPage(Page page, SysDict sysDict) {
        return R.ok(upmsDictService.page(page, Wrappers.query(sysDict)));
    }

    /**
     * 通过字典类型查找字典
     * @param type 类型
     * @return 同类型字典
     */
    @GetMapping("/type/{type}")
    @Cacheable(value = CacheConstants.DICT_DETAILS, key = "#type")
    public R getDictByType(@PathVariable String type) {
        return R.ok(upmsDictItemService.list(Wrappers.<SysDictItem>query().lambda().eq(SysDictItem::getType, type)));
    }

    /**
     * 添加字典
     * @param sysDict 字典信息
     * @return success、false
     */
    @SysLog("添加字典")
    @PostMapping
    @PreAuthorize("@pms.hasPermission('sys_dict_add')")
    public R save(@Valid @RequestBody SysDict sysDict) {
        return R.ok(upmsDictService.save(sysDict));
    }

    /**
     * 删除字典，并且清除字典缓存
     * @param id ID
     * @return R
     */
    @SysLog("删除字典")
    @DeleteMapping("/{id}")
    @PreAuthorize("@pms.hasPermission('sys_dict_del')")
    public R removeById(@PathVariable Integer id) {
        upmsDictService.removeDict(id);
        return R.ok();
    }

    /**
     * 修改字典
     * @param sysDict 字典信息
     * @return success/false
     */
    @PutMapping
    @SysLog("修改字典")
    @PreAuthorize("@pms.hasPermission('sys_dict_edit')")
    public R updateById(@Valid @RequestBody SysDict sysDict) {
        upmsDictService.updateDict(sysDict);
        return R.ok();
    }

    /**
     * 分页查询
     * @param page 分页对象
     * @param sysDictItem 字典项
     * @return
     */
    @GetMapping("/item/page")
    public R getSysDictItemPage(Page page, SysDictItem sysDictItem) {
        return R.ok(upmsDictItemService.page(page, Wrappers.query(sysDictItem)));
    }

    /**
     * 通过id查询字典项
     * @param id id
     * @return R
     */
    @GetMapping("/item/{id}")
    public R getDictItemById(@PathVariable("id") Integer id) {
        return R.ok(upmsDictItemService.getById(id));
    }

    /**
     * 新增字典项
     * @param sysDictItem 字典项
     * @return R
     */
    @SysLog("新增字典项")
    @PostMapping("/item")
    @CacheEvict(value = CacheConstants.DICT_DETAILS, allEntries = true)
    public R save(@RequestBody SysDictItem sysDictItem) {
        return R.ok(upmsDictItemService.save(sysDictItem));
    }

    /**
     * 修改字典项
     * @param sysDictItem 字典项
     * @return R
     */
    @SysLog("修改字典项")
    @PutMapping("/item")
    public R updateById(@RequestBody SysDictItem sysDictItem) {
        return upmsDictItemService.updateDictItem(sysDictItem);
    }

    /**
     * 通过id删除字典项
     * @param id id
     * @return R
     */
    @SysLog("删除字典项")
    @DeleteMapping("/item/{id}")
    public R removeDictItemById(@PathVariable Integer id) {
        return upmsDictItemService.removeDictItem(id);
    }

}
