package com.ly.learn01.mapper;

import com.ly.learn01.domain.dao.banner.Banner;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BannerMapper {


    @Select("SELECT * FROM BANNER")
    List<Banner> getAll();

    @Select("SELECT * FROM BANNER WHERE ID =#{id}")
    Banner getOne(long id);


    @Select("SELECT * FROM BANNER WHERE TITLE =#{title}")
    Banner getOneByTitle(String title);

    @Insert("INSERT INTO BANNER(BANNER.title) VALUES (#{title})")
    void insert(Banner banner);

    @Update("UPDATE BANNER SET title=#{title} WHERE ID = #{id}")
    void update(Banner banner);


    @Delete("DELETE FROM BANNER WHERE ID = #{id}")
    void delete(Banner banner);
}
