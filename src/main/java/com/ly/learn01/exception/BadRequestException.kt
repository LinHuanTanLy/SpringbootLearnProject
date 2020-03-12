package com.ly.learn01.exception

import com.ly.learn01.constant.ErrorCode
import java.util.HashMap

class BadRequestException() : BaseException(ErrorCode.REQUEST_VALIDATION_FAILED, HashMap()) {
}