package com.calendly.service

import com.calendly.manager.AvailabilityManager
import com.calendly.manager.UserManager
import com.calendly.model.Availability
import com.calendly.model.TimeSlot
import com.calendly.model.User
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class AvailabilityService(
    private val availabilityManager: AvailabilityManager,
    private val userService: UserService
) {
    fun setAvailability(emailId: String, availabilities: List<Availability>): List<Availability> {
        ensureUserExists(emailId)
        return availabilityManager.setUserAvailability(emailId, availabilities)
    }

    fun getAvailability(emailId: String): List<Availability> {
        ensureUserExists(emailId)
        return availabilityManager.getUserAvailability(
            emailId = emailId)
    }

    fun findOverlap(userId1: String, userId2: String): List<TimeSlot> {
        ensureUserExists(userId1)
        ensureUserExists(userId2)
        val availability1 = getAvailability(userId1)
        val availability2 = getAvailability(userId2)

        return findOverlapUsingXOR(availability1, availability2)
    }

    private fun findOverlapUsingXOR(availability1: List<Availability>, availability2: List<Availability>): List<TimeSlot> {
        val timeSlots = mutableListOf<TimeSlot>()
        val timePoints = (availability1 + availability2).flatMap { listOf(it.startDateTime, it.endDateTime) }.distinct().sorted()

        var isOverlap = false
        var lastDateTime: LocalDateTime? = null

        for (time in timePoints) {
            val isInSlot1 = availability1.any { it.startDateTime <= time && time < it.endDateTime }
            val isInSlot2 = availability2.any { it.startDateTime <= time && time < it.endDateTime }

            val newOverlap = isInSlot1 xor isInSlot2.not()

            if (newOverlap xor isOverlap) {
                if (newOverlap) {
                    lastDateTime = time
                } else {
                    timeSlots.add(TimeSlot(lastDateTime!!, time))
                }
                isOverlap = newOverlap
            }
        }

        return timeSlots
    }

    private fun ensureUserExists(emailId: String) {
        if (userService.getUser(emailId) == null) {
            userService.saveUser(emailId)
        }
    }
}