package com.calendly.manager

import com.calendly.dal.AvailabilityDAL
import com.calendly.model.Availability
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AvailabilityManager(
    private val availabilityDAL: AvailabilityDAL
) {
    fun setUserAvailability(emailId: String, availabilities: List<Availability>): List<Availability> {
        return availabilities.map {
            availabilityDAL.saveAvailability(
                Availability(
                    emailId=emailId,
                    startDateTime = it.startDateTime,
                    endDateTime = it.endDateTime
                )
            )
        }

    }

    fun getUserAvailability(emailId: String): List<Availability> {
        return availabilityDAL.getAvailabilityByEmailId(emailId)
    }
}