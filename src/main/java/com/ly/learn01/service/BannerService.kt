package com.ly.learn01.service

import com.google.common.collect.ImmutableMap
import com.ly.learn01.api.CommResult
import com.ly.learn01.constant.ErrorCode
import com.ly.learn01.domain.repository.banner.BannerRepository
import com.ly.learn01.domain.dao.banner.Banner
import com.ly.learn01.exception.BadRequestException
import com.ly.learn01.exception.ResourceNotFoundException
import com.ly.learn01.mapper.BannerMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BannerService {

    @Autowired
    private lateinit var bannerRepository: BannerRepository
    @Autowired
    private lateinit var bannerMapper: BannerMapper

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


    fun getBannerFromMybatis(): CommResult<List<Banner>> {
        return CommResult.suc(bannerMapper.all)
    }

    fun getOneBannerFromMybatis(id: Long): CommResult<Banner> {
        val result = bannerMapper.getOne(id)
        return if (result != null) {
            CommResult.suc(result)
        } else {
            CommResult.fail(ErrorCode.RESOURCE_NOT_FOUND)
        }
    }

    fun insertIntoWithMyBatis(banner: Banner): CommResult<Banner> {
        bannerMapper.insert(banner)
        val result = bannerMapper.getOneByTitle(banner.title)
        return CommResult.suc(result)
    }

    fun updateWithMyBatis(banner: Banner): CommResult<Banner> {
        bannerMapper.update(banner)
        val result = bannerMapper.getOneByTitle(banner.title)
        return CommResult.suc(result)
    }

    fun deleteWithMyBatis(banner: Banner): CommResult<Banner> {
        bannerMapper.delete(banner)
        val result = bannerMapper.getOneByTitle(banner.title)
        return CommResult.suc(result)
    }

}