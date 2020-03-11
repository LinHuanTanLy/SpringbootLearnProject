package com.ly.learn01.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
@ResponseBody
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun handleAppException(ex: BaseException, request: HttpServletRequest): ResponseEntity<Any> {
        val representation = ErrorResponse(ex, request.requestURI)
        println("GlobalExceptionHandler----${representation.toString()}")
        println("GlobalExceptionHandler----${ex.message}")
        println("GlobalExceptionHandler----${request.requestURI}")
        val result: ResponseEntity<Any> = ResponseEntity(representation, HttpHeaders(), ex.error.status)
        println("result= $result")
        return result
    }
}