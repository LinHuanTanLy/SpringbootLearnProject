package com.ly.learn01.controller.blog;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ly.learn01.api.CommResult;
import com.ly.learn01.domain.dao.blog.Article;
import com.ly.learn01.service.blog.ArticleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "文章列表")
@RequestMapping("blog/article")
public class ArticleController {


    @Autowired
    ArticleService articleService;

    @GetMapping("findAllArticle")
    public PageInfo<Article> findAllArticle(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.getAllArticle();
        return new PageInfo<>(list);
    }
}
