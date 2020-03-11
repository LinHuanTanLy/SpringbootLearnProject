package com.ly.learn01.dao.banner;

import com.ly.learn01.domain.banner.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerRepository extends JpaRepository<Banner,Long> {


}
