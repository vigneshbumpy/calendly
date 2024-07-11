package com.calendly.manager

import com.calendly.dal.AvailabilityDAL
import com.calendly.model.Availability
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * Availability manager
 *
 * @property availabilityDAL
 * @constructor Create empty Availability manager
 */
@Component
class AvailabilityManager(
    private val availabilityDAL: AvailabilityDAL
) {
    /**
     * Set user availability
     *
     * @param emailId
     * @param availabilities
     * @return
     */
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

    /**
     * Get user availability
     *
     * @param emailId
     * @return
     */
    fun getUserAvailability(emailId: String): List<Availability> {
        return availabilityDAL.getAvailabilityByEmailId(emailId)
    }
}