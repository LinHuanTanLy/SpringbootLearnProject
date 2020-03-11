package com.ly.learn01.controller

import com.ly.learn01.domain.dao.banner.Banner
import com.ly.learn01.service.BannerService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/index")
@Api(value = "首页模块")
class IndexController {
    @Autowired
    private lateinit var indexService: BannerService

    @GetMapping("/getBanner")
    @ApiOperation("查询所有的Banner")
    fun getBanner(): List<Banner> = indexService.getBanner()

    @PostMapping("/addBanner")
    @ApiOperation("添加banner数据")
    fun addBanner(@RequestParam(name = "title") title: String): Banner = indexService.addBanner(Banner(title = title))

    @PutMapping("/updateBanner")
    @ApiOperation("更新banner数据")
    fun updateBanner(@RequestParam(name = "id") id: Long, @RequestParam(name = "title") title: String): Banner = indexService.updateBanner(id, title)

    @DeleteMapping("deleteBanner")
    @ApiOperation("删除banner")
    fun deleteBanner(@RequestParam(name = "id") id: Long): Boolean = indexService.deleteBanner(id)
}