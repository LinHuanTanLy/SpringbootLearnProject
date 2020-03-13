package com.ly.learn01.controller.blog;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.learn01.api.CommResult;
import com.ly.learn01.constant.ErrorCode;
import com.ly.learn01.domain.dao.blog.Article;
import com.ly.learn01.exception.ResourceNotFoundException;
import com.ly.learn01.service.blog.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "文章列表")
@RequestMapping("blog/article")
public class ArticleController {


    @Autowired
    ArticleService articleService;

    @GetMapping("findAllArticle")
    @ApiOperation("查找所有的已经发布的文章")
    public CommResult<PageInfo<Article>> findAllArticle(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.getAllArticle();
        return CommResult.suc(new PageInfo<>(list));
    }


    @GetMapping("findAllArticleByState")
    @ApiOperation("根据状态查找所有的文章")
    public CommResult<PageInfo<Article>> findAllArticleByState(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize, @RequestParam("state") int state) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.getAllArticleByState(state);
        return CommResult.suc(new PageInfo<>(list));
    }

    @GetMapping("findOneById")
    @ApiOperation("根据id查找文章")
    public CommResult<Article> findOneById(@RequestParam("id") int id) {
        Article article = articleService.getArticle(id);
        if (article == null) {
            throw new ResourceNotFoundException();
        } else {
            return CommResult.suc(article);
        }
    }

    @PutMapping("updateArticleState")
    @ApiOperation("根据id更新文章状态")
    public CommResult<Integer> updateArticle(@RequestParam("id") int id,
                                             @RequestParam("state") @ApiParam("文章状态：0表示草稿箱，1表示已发表，2表示已删除") int state) {
        int result = articleService.updateArticle(id, state);
        /// update 方法的话，有个int类型的返回值，也就是返回匹配到的数据，可以粗略用于校验是否update suc
        if (result == 1) {
            return CommResult.suc(1, "更新成功");
        } else {
            return CommResult.fail(ErrorCode.RESOURCE_NOT_FOUND);
        }
    }

    @PostMapping("addArticle")
    @ApiOperation("添加数据")
    public CommResult<Integer> addArticle(@RequestBody Article article) {
        int result = articleService.addArticle(article);
        if (result == 1) {
            return CommResult.suc(1, "添加成功");
        } else {
            return CommResult.fail(ErrorCode.RESOURCE_NOT_FOUND);
        }
    }
}
