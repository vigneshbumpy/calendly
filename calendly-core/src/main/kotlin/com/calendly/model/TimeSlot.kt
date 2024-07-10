package com.calendly.model

import java.time.LocalDateTime

data class TimeSlot(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime
)
