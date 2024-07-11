package com.calendly.dal

import com.calendly.dao.AvailabilityDao
import com.calendly.model.Availability
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AvailabilityDAL(
    private val availabilityDao: AvailabilityDao
) {
    fun saveAvailability(availability: Availability): Availability {
        return availabilityDao.save(availability)
    }

    fun getAvailabilityByUserIdAndDateRange(emailId: String, start: LocalDateTime, end: LocalDateTime): List<Availability> {
        return availabilityDao.findByEmailIdAndStartDateTimeBetween(emailId, start, end)
    }

    fun getAvailabilityByEmailId(emailId: String): List<Availability> {
        return availabilityDao.findByEmailId(emailId)
    }
}