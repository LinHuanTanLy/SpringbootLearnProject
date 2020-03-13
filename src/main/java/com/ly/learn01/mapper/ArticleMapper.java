package com.ly.learn01.mapper;

import com.github.pagehelper.Page;
import com.ly.learn01.domain.dao.blog.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper {


    @Select("SELECT * FROM ARTICLE where state =1")
    public Page<Article> getAllArticle();
}
