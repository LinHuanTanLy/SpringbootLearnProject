package com.ly.learn01.exception

import com.ly.learn01.api.CommResult
import com.ly.learn01.constant.ErrorCode
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody

@ControllerAdvice  //异常处理
class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(BaseException::class)
    fun handleAppException(ex: BaseException): CommResult<ErrorCode> {
        return CommResult.fail(ex.error)
    }
}