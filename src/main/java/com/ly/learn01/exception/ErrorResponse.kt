package com.ly.learn01.exception

import java.time.Instant

class ErrorResponse(private val code: Int, private val status: Int, private val message: String,
                    private val path: String, private val timestamp: Instant, private val data: HashMap<String, Any>) {
    constructor(e: BaseException, path: String) : this(e.error.code, e.error.status.value(), e.error.message, path, Instant.now(), e.data)
    constructor(code: Int, status: Int, message: String, path: String, data: HashMap<String, Any>) : this(code, status, message, path, Instant.now(), data)

}