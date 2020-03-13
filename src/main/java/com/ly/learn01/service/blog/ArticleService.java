package com.ly.learn01.service.blog;

import com.ly.learn01.api.CommResult;
import com.ly.learn01.domain.dao.blog.Article;
import com.ly.learn01.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;


    public List<Article> getAllArticle() {
        return articleMapper.getAllArticle();
    }

}
