package org.ituac.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.ituac.api.cms.model.entity.CmsPosts;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.service.CmsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文章控制
 */
@RestController
@RequestMapping("/cms/posts")
@Slf4j
public class CmsPostsController {

    @Autowired
    CmsPostsService cmsPostsService;

    /**
     * 查询
     */
    @RequestMapping("/list")
    public ResponseEntity<IPage<CmsPosts>> list(@RequestBody PageDto<CmsPosts> pageDto) {
        IPage<CmsPosts> cmsPosts = cmsPostsService.list(pageDto);
        return ResponseEntity.ok(cmsPosts);
    }

    /**
     * 增加
     */
    @RequestMapping("/save")
    public ResponseEntity save(@RequestBody CmsPosts cmsPosts) {
        return (ResponseEntity) (cmsPostsService.save(cmsPosts) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody CmsPosts cmsPosts) {
        return (ResponseEntity) (cmsPostsService.updateById(cmsPosts) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public ResponseEntity remove(@RequestBody CmsPosts cmsPosts) {
        return (ResponseEntity) (cmsPostsService.removeById(cmsPosts.getId()) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

}
