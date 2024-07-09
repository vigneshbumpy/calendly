package com.calendly.dal

import com.calendly.dao.AvailabilityRepository
import com.calendly.model.AvailabilitySlot
import com.calendly.model.User
import org.springframework.stereotype.Component

@Component
class AvailabilityDAL(private val availabilityRepository: AvailabilityRepository) {
    fun setAvailability(user: User, slots: List<java.time.Instant>) {
        availabilityRepository.deleteAll(availabilityRepository.findByUser(user))
        availabilityRepository.saveAll(slots.map { AvailabilitySlot(user = user, startTime = it) })
    }

    fun getAvailability(user: User): List<java.time.Instant> =
        availabilityRepository.findByUser(user).map { it.startTime }

    fun findOverlap(user1: User, user2: User): List<java.time.Instant> =
        availabilityRepository.findOverlap(user1, user2).map { it.startTime }
}