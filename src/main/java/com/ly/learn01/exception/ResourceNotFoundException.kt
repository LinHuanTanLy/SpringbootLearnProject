package com.ly.learn01.exception

import com.ly.learn01.constant.ErrorCode
import java.lang.RuntimeException

class ResourceNotFoundException(data: HashMap<String, Any>) : BaseException(ErrorCode.RESOURCE_NOT_FOUND, data) {


    constructor() : this(HashMap())

}