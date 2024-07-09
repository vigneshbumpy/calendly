package dao

import model.AvailabilitySlot
import model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface AvailabilityRepository : JpaRepository<AvailabilitySlot, Long> {
    fun findByUser(user: User): List<AvailabilitySlot>

    @Query("SELECT a FROM AvailabilitySlot a WHERE a.user = :user1 AND a.startTime IN (SELECT b.startTime FROM AvailabilitySlot b WHERE b.user = :user2)")
    fun findOverlap(user1: User, user2: User): List<AvailabilitySlot>
}