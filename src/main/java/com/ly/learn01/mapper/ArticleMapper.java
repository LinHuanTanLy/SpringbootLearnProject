package com.ly.learn01.mapper;

import com.github.pagehelper.Page;
import com.ly.learn01.domain.dao.blog.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleMapper {


    @Select("SELECT * FROM ARTICLE where state =1")
    public Page<Article> getAllArticle();

    @Select("SELECT * FROM ARTICLE WHERE state = #{state}")
    public Page<Article> getAllArticleByState(int state);

    @Select("SELECT * FROM ARTICLE  WHERE ID=#{id}")
    public Article getArticleById(int id);

    @Update("update article set state=#{state} where id =#{id}")
    public int updateArticleState(int id, int state);

    @Insert("insert into ARTICLE SET TITLE=#{title}")
    public int addArticle(Article article);
}
