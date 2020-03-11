package com.ly.learn01.service

import com.google.common.collect.ImmutableMap
import com.ly.learn01.domain.repository.banner.BannerRepository
import com.ly.learn01.domain.dao.banner.Banner
import com.ly.learn01.exception.ResourceNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BannerService {

    @Autowired
    private lateinit var bannerRepository: BannerRepository

    fun addBanner(banner: Banner): Banner {
        return bannerRepository.saveAndFlush(banner)
    }


    fun getBanner(): List<Banner> {
        return bannerRepository.findAll()
    }

    fun updateBanner(id: Long, title: String): Banner {
        val result = getBannerById(id)
        result.title = title
        return bannerRepository.save(result)

    }

    fun deleteBanner(id: Long): Boolean {
        val result = getBannerById(id)
        bannerRepository.delete(result)
        return bannerRepository.findById(id).isEmpty
    }

    /**
     * 获取单个banner
     */
    private fun getBannerById(id: Long): Banner {
        val bannerOptional = bannerRepository.findById(id)
        if (bannerOptional.isEmpty) {
            val map = HashMap<String, Any>()
            map["资源id没有找到"] = id
            throw ResourceNotFoundException(map)
        } else {
            return bannerOptional.get()
        }
    }

}