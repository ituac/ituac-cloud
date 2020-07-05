package org.ituac.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.ituac.api.cms.model.entity.CmsTags;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.service.CmsTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标签控制类
 */
@RestController
@RequestMapping("/cms/tags")
public class CmsTagsController {
    @Autowired
    private CmsTagsService cmsTagsService;

    /**
     * 查询
     */
    @RequestMapping("/list")
    public ResponseEntity<IPage<CmsTags>> list(@RequestBody PageDto<CmsTags> pageDto) {
        IPage<CmsTags> cmsTags = cmsTagsService.list(pageDto);
        return ResponseEntity.ok(cmsTags);
    }

    /**
     * 增加
     */
    @RequestMapping("/save")
    public ResponseEntity save(@RequestBody CmsTags cmsTags) {
        return (ResponseEntity) (cmsTagsService.save(cmsTags) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody CmsTags cmsTags) {
        return (ResponseEntity) (cmsTagsService.updateById(cmsTags) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public ResponseEntity remove(@RequestBody CmsTags cmsTags) {
        return (ResponseEntity) (cmsTagsService.removeById(cmsTags.getId()) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }
}


