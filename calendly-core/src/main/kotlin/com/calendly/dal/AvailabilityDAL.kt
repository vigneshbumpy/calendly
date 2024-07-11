package com.calendly.dal

import com.calendly.dao.AvailabilityDao
import com.calendly.model.Availability
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * Availability DAL
 *
 * @property availabilityDao
 * @constructor Create empty Availability DAL
 */
@Component
class AvailabilityDAL(
    private val availabilityDao: AvailabilityDao
) {
    /**
     * Save availability
     *
     * @param availability
     * @return
     */
    fun saveAvailability(availability: Availability): Availability {
        return availabilityDao.save(availability)
    }

    /**
     * Get availability by user id and date range
     *
     * @param emailId
     * @param start
     * @param end
     * @return
     */
    fun getAvailabilityByUserIdAndDateRange(emailId: String, start: LocalDateTime, end: LocalDateTime): List<Availability> {
        return availabilityDao.findByEmailIdAndStartDateTimeBetween(emailId, start, end)
    }

    /**
     * Get availability by email id
     *
     * @param emailId
     * @return
     */
    fun getAvailabilityByEmailId(emailId: String): List<Availability> {
        return availabilityDao.findByEmailId(emailId)
    }
}