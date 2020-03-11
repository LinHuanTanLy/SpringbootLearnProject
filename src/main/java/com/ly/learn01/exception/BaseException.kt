package com.ly.learn01.exception

import com.ly.learn01.constant.ErrorCode
import org.springframework.util.ObjectUtils


abstract class BaseException(val error: ErrorCode,  val data: HashMap<String, Any>) : RuntimeException() {


}