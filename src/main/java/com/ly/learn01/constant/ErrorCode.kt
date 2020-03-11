package com.ly.learn01.constant

import org.springframework.http.HttpStatus

enum class ErrorCode( val code: Int,  val status: HttpStatus,  val message: String) {

    RESOURCE_NOT_FOUND(1001, HttpStatus.NOT_FOUND, "资源未找到"),
    REQUEST_VALIDATION_FAILED(1002, HttpStatus.BAD_REQUEST, "请求数据格式验证失败")
}