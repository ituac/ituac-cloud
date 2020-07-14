package org.ituac.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.ituac.api.cms.model.entity.Posts;
import org.ituac.api.cms.model.entity.PageDto;
import org.ituac.cms.service.CmsPostsService;
import org.ituac.common.kern.util.R;
import org.ituac.common.security.annotation.Inner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangpeng
 * 内容管理
 *
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
@Api(value = "posts", tags = "内容管理模块")
public class CmsPostsController {

    Logger logger = LoggerFactory.getLogger(CmsPostsController.class);

    @Autowired
    CmsPostsService cmsPostsService;

    /**
     * 获取指定文章信息
     * @return 文章信息
     */
    @GetMapping("/info/{postid}")
    public R info(@PathVariable String postid) {
        //cmsPostsService.list();
        if(postid.equals("2")){
            Posts posts = new Posts();
            posts.setId(2);
            posts.setTitle("cehi2");
            return R.ok(posts);
        }else{
            Posts posts = new Posts();
            posts.setId(3);
            posts.setTitle("cehi3");
            return R.ok(posts);
        }
    }

    /**
     * 查询
     */
    @RequestMapping("/list")
    public ResponseEntity<IPage<Posts>> list(@RequestBody PageDto<Posts> pageDto) {
        IPage<Posts> cmsPosts = cmsPostsService.list(pageDto);
        return ResponseEntity.ok(cmsPosts);
    }

    /**
     * 增加
     */
    @RequestMapping("/save")
    public ResponseEntity save(@RequestBody Posts posts) {
        return (ResponseEntity) (cmsPostsService.save(posts) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody Posts posts) {
        return (ResponseEntity) (cmsPostsService.updateById(posts) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

    /**
     * 删除
     */
    @RequestMapping("/remove")
    public ResponseEntity remove(@RequestBody Posts posts) {
        return (ResponseEntity) (cmsPostsService.removeById(posts.getId()) ? ResponseEntity.ok() : ResponseEntity.badRequest());
    }

}
