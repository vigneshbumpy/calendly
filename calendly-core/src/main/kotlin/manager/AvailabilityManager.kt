package manager

import dal.AvailabilityDAL
import model.User
import org.springframework.stereotype.Component

@Component
class AvailabilityManager(private val availabilityDAL: AvailabilityDAL) {
    fun setAvailability(user: User, slots: List<java.time.Instant>) =
        availabilityDAL.setAvailability(user, slots)

    fun getAvailability(user: User): List<java.time.Instant> =
        availabilityDAL.getAvailability(user)

    fun findOverlap(user1: User, user2: User): List<java.time.Instant> =
        availabilityDAL.findOverlap(user1, user2)
}