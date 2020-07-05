package org.ituac.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.ituac.api.cms.model.entity.CmsCategories;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.service.CmsCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类别控制类
 */
@RestController
@RequestMapping("/cms/categories")
@Slf4j
public class CmsCategoriesController {
    @Autowired
    CmsCategoriesService cmsCategoriesService;

    /**
     * 查询
     */
    @RequestMapping("/list")
    public ResponseEntity<IPage<CmsCategories>> list(@RequestBody PageDto<CmsCategories> pageDto) {
        IPage<CmsCategories> cmsCategories = cmsCategoriesService.list(pageDto);
        return ResponseEntity.ok(cmsCategories);
    }

    /**
     * 增加
     */
    @RequestMapping("/save")
    public ResponseEntity save(@RequestBody CmsCategories cmsCategories) {
        return (ResponseEntity) (cmsCategoriesService.save(cmsCategories) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody CmsCategories cmsCategories) {
        return (ResponseEntity) (cmsCategoriesService.updateById(cmsCategories) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public ResponseEntity remove(@RequestBody CmsCategories cmsCategories) {
        return (ResponseEntity) (cmsCategoriesService.removeById(cmsCategories.getId()) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }
}
