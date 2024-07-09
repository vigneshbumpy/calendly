package service

import manager.AvailabilityManager
import manager.UserManager
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CalendarService(
    private val userManager: UserManager,
    private val availabilityManager: AvailabilityManager
) {
    fun setUserAvailability(userId: String, slots: List<Instant>) {
        val user = userManager.getOrCreateUser(userId)
        availabilityManager.setAvailability(user, slots)
    }

    fun getUserAvailability(userId: String): List<Instant> {
        val user = userManager.getOrCreateUser(userId)
        return availabilityManager.getAvailability(user)
    }

    fun findScheduleOverlap(userId1: String, userId2: String): List<Instant> {
        val user1 = userManager.getOrCreateUser(userId1)
        val user2 = userManager.getOrCreateUser(userId2)
        return availabilityManager.findOverlap(user1, user2)
    }
}