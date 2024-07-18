package com.calendly.service

import com.calendly.exception.CustomException
import com.calendly.manager.AvailabilityManager
import com.calendly.model.Availability
import com.calendly.model.TimeSlot
import org.springframework.stereotype.Service

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
        //todo: This can be improved by just selecting the availability > than current data time or a custom date time of the caller
        val availability1 = getAvailability(userId1).map { TimeSlot(it.startDateTime, it.endDateTime) }
        val availability2 = getAvailability(userId2).map { TimeSlot(it.startDateTime, it.endDateTime) }

        return findOverlapAvailability(availability1, availability2)
    }

    fun findOverlapAvailability(availability1: List<TimeSlot>, availability2: List<TimeSlot>): List<TimeSlot> {
        val overlaps = mutableListOf<TimeSlot>()

        for (slot1 in availability1) {
            for (slot2 in availability2) {
                val overlapStart = maxOf(slot1.startTime, slot2.startTime)
                val overlapEnd = minOf(slot1.endTime, slot2.endTime)

                if (overlapStart < overlapEnd) {
                    overlaps.add(TimeSlot(overlapStart, overlapEnd))
                }
            }
        }

        return mergeOverlappingSlots(overlaps)
    }

    fun mergeOverlappingSlots(slots: List<TimeSlot>): List<TimeSlot> {
        if (slots.isEmpty()) return emptyList()

        val sortedSlots = slots.sortedBy { it.startTime }
        val mergedSlots = mutableListOf<TimeSlot>()
        var currentSlot = sortedSlots[0]

        for (i in 1 until sortedSlots.size) {
            val nextSlot = sortedSlots[i]
            if (currentSlot.endTime >= nextSlot.startTime) {
                currentSlot = TimeSlot(currentSlot.startTime, maxOf(currentSlot.endTime, nextSlot.endTime))
            } else {
                mergedSlots.add(currentSlot)
                currentSlot = nextSlot
            }
        }
        mergedSlots.add(currentSlot)

        return mergedSlots
    }

    private fun ensureUserExists(emailId: String) {
        if (userService.getUser(emailId) == null) {
            throw CustomException("User $emailId does not exist")
        }
    }
}