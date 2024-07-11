package com.calendly.dao

import com.calendly.model.Availability
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

/**
 * Availability dao
 *
 * @constructor Create empty Availability dao
 */
@Repository
interface AvailabilityDao : JpaRepository<Availability, Long> {
    /**
     * Find by email id and start date time between
     *
     * @param emailId
     * @param start
     * @param end
     * @return
     */
    fun findByEmailIdAndStartDateTimeBetween(emailId: String, start: LocalDateTime, end: LocalDateTime): List<Availability>

    /**
     * Find by email id
     *
     * @param emailId
     * @return
     */
    fun findByEmailId(emailId: String): List<Availability>
}