package com.calendly.dao

import com.calendly.model.Availability
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface AvailabilityDao : JpaRepository<Availability, Long> {
    fun findByEmailIdAndStartDateTimeBetween(emailId: String, start: LocalDateTime, end: LocalDateTime): List<Availability>
}