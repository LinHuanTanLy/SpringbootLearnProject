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


    public List<Article> getAllArticleByState(int state) {
        return articleMapper.getAllArticleByState(state);
    }


    public Article getArticle(int id) {
        return articleMapper.getArticleById(id);
    }

    public int updateArticle(int id, int state) {
        return articleMapper.updateArticleState(id, state);
    }

    public int addArticle(Article article) {
        return articleMapper.addArticle(article);
    }
}
