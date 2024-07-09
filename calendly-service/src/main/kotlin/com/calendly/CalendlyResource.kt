package com.calendly

import org.springframework.boot.context.properties.bind.Bindable.mapOf
import org.springframework.web.bind.annotation.*
import service.CalendarService
import java.time.Instant

@RestController
@RequestMapping("/api/v1")
class CalendarResource(private val calendarService: CalendarService) {

    @PostMapping("/users/{userId}/availability")
    fun setUserAvailability(@PathVariable userId: String, @RequestBody slots: List<String>): Map<String, String> {
        val instantSlots = slots.map { Instant.parse(it) }
        calendarService.setUserAvailability(userId, instantSlots)
        return mapOf("message" to "Availability set successfully")
    }

    @GetMapping("/users/{userId}/availability")
    fun getUserAvailability(@PathVariable userId: String): Map<String, List<String>> {
        val availability = calendarService.getUserAvailability(userId).map { it.toString() }
        return mapOf("slots" to availability)
    }

    @GetMapping("/schedule/overlap")
    fun findScheduleOverlap(@RequestParam user1: String, @RequestParam user2: String): Map<String, List<String>> {
        val overlap = calendarService.findScheduleOverlap(user1, user2).map { it.toString() }
        return mapOf("overlap" to overlap)
    }
}