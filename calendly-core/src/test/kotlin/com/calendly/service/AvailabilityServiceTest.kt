package com.calendly.service

import com.calendly.manager.AvailabilityManager
import com.calendly.model.Availability
import com.calendly.model.TimeSlot
import com.calendly.service.AvailabilityService
import com.calendly.service.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class AvailabilityServiceTest {

    private lateinit var availabilityManager: AvailabilityManager
    private lateinit var userService: UserService
    private lateinit var availabilityService: AvailabilityService

    @BeforeEach
    fun setup() {
        availabilityManager = mockk()
        userService = mockk()
        availabilityService = AvailabilityService(availabilityManager, userService)
    }

    @Test
    fun `setAvailability should create user if not exists and set availability`() {
        val email = "test@example.com"
        val availabilities = listOf(
            Availability(1, email, LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        )

        every { userService.getUser(email) } returns null
        every { userService.saveUser(email) } returns mockk()
        every { availabilityManager.setUserAvailability(email, availabilities) } returns availabilities

        val result = availabilityService.setAvailability(email, availabilities)

        verify { userService.saveUser(email) }
        verify { availabilityManager.setUserAvailability(email, availabilities) }
        assertEquals(availabilities, result)
    }

    @Test
    fun `getAvailability should return user availabilities`() {
        val email = "test@example.com"
        val availabilities = listOf(
            Availability(1, email, LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        )

        every { userService.getUser(email) } returns mockk()
        every { availabilityManager.getUserAvailability(email) } returns availabilities

        val result = availabilityService.getAvailability(email)

        verify { availabilityManager.getUserAvailability(email) }
        assertEquals(availabilities, result)
    }

    @Test
    fun `findOverlap should return overlapping time slots`() {
        val user1 = "setuser1@example.com"
        val user2 = "setuser2@example.com"
        val now = LocalDateTime.now()
        val availabilities1 = listOf(
            Availability(1, user1, now.plusHours(1),now.plusHours(2)),
            Availability(2, user2, now.plusHours(4), now.plusHours(6))
        )
        val availabilities2 = listOf(
            Availability(1, user1,now.plusHours(1), now.plusHours(3)),
            Availability(2, user2, now.plusHours(5), now.plusHours(7))
        )
        val expectedOverlap = listOf(
            TimeSlot(now.plusHours(1), now.plusHours(2)),
            TimeSlot(now.plusHours(5), now.plusHours(6))
        )

        every { userService.getUser(any()) } returns mockk()
        every { availabilityManager.getUserAvailability(user1) } returns availabilities1
        every { availabilityManager.getUserAvailability(user2) } returns availabilities2

        val result = availabilityService.findOverlap(user1, user2)

        assertEquals(expectedOverlap, result)
    }

    @Test
    fun `findOverlap should return empty list when no overlap`() {
        val user1 = "overlapuser1@example.com"
        val user2 = "overlapuser2@example.com"
        val now = LocalDateTime.now()
        val availabilities1 = listOf(
            Availability(1, user1,now, now.plusHours(1))
        )
        val availabilities2 = listOf(
            Availability(2, user2,now.plusHours(2), now.plusHours(3))
        )

        every { userService.getUser(any()) } returns mockk()
        every { availabilityManager.getUserAvailability(user1) } returns availabilities1
        every { availabilityManager.getUserAvailability(user2) } returns availabilities2

        val result = availabilityService.findOverlap(user1, user2)

        assertTrue(result.isEmpty())
    }
}