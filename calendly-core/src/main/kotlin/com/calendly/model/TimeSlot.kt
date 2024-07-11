package com.calendly.model

import java.time.LocalDateTime

/**
 * Time slot
 *
 * @property startTime
 * @property endTime
 * @constructor Create empty Time slot
 */
data class TimeSlot(
    val startTime: LocalDateTime = LocalDateTime.now(),
    val endTime: LocalDateTime = LocalDateTime.now()
)
