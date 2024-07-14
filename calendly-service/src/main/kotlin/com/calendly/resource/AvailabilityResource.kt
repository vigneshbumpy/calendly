package com.calendly.resource

import com.calendly.model.Availability
import com.calendly.model.TimeSlot
import com.calendly.service.AvailabilityService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * Availability resource
 *
 * @property availabilityService
 * @constructor Create empty Availability resource
 */
@RestController
@RequestMapping("/api/availability")

class AvailabilityResource(private val availabilityService: AvailabilityService) {

    /**
     * Set availability
     *
     * @param emailId
     * @param availabilities
     * @return
     */
    @PostMapping("/set/{emailId}")
    fun setAvailability(@PathVariable emailId: String, @RequestBody availabilities: List<Availability>): ResponseEntity<List<Availability>> {
        val savedAvailabilities = availabilityService.setAvailability(emailId, availabilities)
        return ResponseEntity.ok(savedAvailabilities)
    }

    /**
     * Get availability
     *
     * @param emailId
     * @return
     */
    @GetMapping("/get/{emailId}")
    fun getAvailability(
        @PathVariable emailId: String
    ): ResponseEntity<List<Availability>> {
        val availabilities = availabilityService.getAvailability(emailId)
        return ResponseEntity.ok(availabilities)
    }

    /**
     * Find overlap
     *
     * @param emailId1
     * @param emailId2
     * @return
     */
    @GetMapping("/overlap")
    fun findOverlap(
        @RequestParam emailId1: String,
        @RequestParam emailId2: String
    ): ResponseEntity<List<TimeSlot>> {
        val overlap = availabilityService.findOverlap(emailId1, emailId2)
        return ResponseEntity.ok(overlap)
    }
}