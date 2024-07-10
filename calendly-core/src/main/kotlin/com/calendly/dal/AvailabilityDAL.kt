package com.calendly.dal

import com.calendly.dao.AvailabilityDao
import com.calendly.model.Availability
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AvailabilityDAL(private val repository: AvailabilityDao) {
    fun saveAvailability(availability: Availability): Availability {
        return repository.save(availability)
    }

    fun getAvailabilityByUserIdAndDateRange(emailId: String, start: LocalDateTime, end: LocalDateTime): List<Availability> {
        return repository.findByEmailIdAndStartDateTimeBetween(emailId, start, end)
    }
}