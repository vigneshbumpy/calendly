package com.calendly.exception

class CustomException(message: String) : RuntimeException(message)

data class ErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)