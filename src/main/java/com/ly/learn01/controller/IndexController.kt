package com.ly.learn01.controller

import com.ly.learn01.api.CommResult
import com.ly.learn01.domain.dao.banner.Banner
import com.ly.learn01.service.BannerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/index")
@Api(value = "首页模块")
class IndexController {
    @Autowired
    private lateinit var indexService: BannerService

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<Any, Any>


    @GetMapping("/getBanner")
    @ApiOperation("查询所有的Banner")
    fun getBanner(): CommResult<List<Banner>>? = CommResult.suc(indexService.getBanner())

    @PostMapping("/addBanner")
    @ApiOperation("添加banner数据")
    fun addBanner(@RequestParam(name = "title") title: String): CommResult<Banner> = CommResult.suc(indexService.addBanner(Banner(title = title)))

    @PutMapping("/updateBanner")
    @ApiOperation("更新banner数据")
    fun updateBanner(@RequestParam(name = "id") id: Long, @RequestParam(name = "title") title: String): CommResult<Banner> = CommResult.suc(indexService.updateBanner(id, title))

    @DeleteMapping("deleteBanner")
    @ApiOperation("删除banner")
    fun deleteBanner(@RequestParam(name = "id", required = false, defaultValue = "-1") id: Long): CommResult<Boolean> = CommResult.suc(indexService.deleteBanner(id))


    @GetMapping("getFromRedis")
    @ApiOperation("从redis中获取存储的数据")
    fun getFromRedis(): CommResult<Any?>? {
        val tempBanner = Banner("凌宇")
        tempBanner.id = 2
        redisTemplate.opsForValue().set("name", tempBanner)
        val result = redisTemplate.opsForValue().get("name")
        return CommResult.suc(result)
    }
}