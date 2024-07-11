package com.calendly.model

import java.time.LocalDateTime

data class TimeSlot(
    val startTime: LocalDateTime = LocalDateTime.now(),
    val endTime: LocalDateTime = LocalDateTime.now()
)
