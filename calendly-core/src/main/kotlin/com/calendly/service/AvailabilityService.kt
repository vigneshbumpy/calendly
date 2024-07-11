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

/**
 * Availability service
 *
 * @property availabilityManager
 * @property userService
 * @constructor Create empty Availability service
 */
@Service
class AvailabilityService(
    private val availabilityManager: AvailabilityManager,
    private val userService: UserService
) {
    /**
     * Set availability
     *
     * @param emailId
     * @param availabilities
     * @return
     */
    fun setAvailability(emailId: String, availabilities: List<Availability>): List<Availability> {
        ensureUserExists(emailId)
        return availabilityManager.setUserAvailability(emailId, availabilities)
    }

    /**
     * Get availability
     *
     * @param emailId
     * @return
     */
    fun getAvailability(emailId: String): List<Availability> {
        ensureUserExists(emailId)
        return availabilityManager.getUserAvailability(
            emailId = emailId)
    }

    /**
     * Find overlap
     *
     * @param userId1
     * @param userId2
     * @return
     */
    fun findOverlap(userId1: String, userId2: String): List<TimeSlot> {
        ensureUserExists(userId1)
        ensureUserExists(userId2)
        val availability1 = getAvailability(userId1).map { TimeSlot(it.startDateTime, it.endDateTime) }
        val availability2 = getAvailability(userId2).map { TimeSlot(it.startDateTime, it.endDateTime) }

        return findOverlapAvailability(availability1, availability2)
    }

    fun findOverlapAvailability(availability1: List<TimeSlot>, availability2: List<TimeSlot>): List<TimeSlot> {
        val events = mutableListOf<Event>()

        // Add start and end events for both users
        for (slot in availability1) {
            events.add(Event(slot.startTime, 1, true))
            events.add(Event(slot.endTime, 1, false))
        }
        for (slot in availability2) {
            events.add(Event(slot.startTime, 2, true))
            events.add(Event(slot.endTime, 2, false))
        }

        // Sort events by time
        events.sortBy { it.time }

        val overlaps = mutableListOf<TimeSlot>()
        var user1Active = false
        var user2Active = false
        var overlapStart: LocalDateTime? = null

        for (event in events) {
            when {
                event.userId == 1 -> user1Active = event.isStart
                event.userId == 2 -> user2Active = event.isStart
            }

            if (user1Active && user2Active && overlapStart == null) {
                overlapStart = event.time
            } else if ((!user1Active || !user2Active) && overlapStart != null) {
                overlaps.add(TimeSlot(overlapStart, event.time))
                overlapStart = null
            }
        }

        return overlaps
    }

    data class Event(val time: LocalDateTime, val userId: Int, val isStart: Boolean)

    private fun ensureUserExists(emailId: String) {
        if (userService.getUser(emailId) == null) {
            throw Exception("User $emailId does not exist")
        }
    }
}