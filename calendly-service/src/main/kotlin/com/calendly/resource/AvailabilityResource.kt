import com.calendly.model.Availability
import com.calendly.model.TimeSlot
import com.calendly.service.AvailabilityService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class AvailabilityResource(private val availabilityService: AvailabilityService) {

    @PostMapping("/availability/{emailId}")
    fun setAvailability(@PathVariable emailId: String, @RequestBody availabilities: List<Availability>): ResponseEntity<List<Availability>> {
        val savedAvailabilities = availabilityService.setAvailability(emailId, availabilities)
        return ResponseEntity.ok(savedAvailabilities)
    }

    @GetMapping("/availability/{userId}")
    fun getAvailability(
        @PathVariable emailId: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ): ResponseEntity<List<Availability>> {
        val availabilities = availabilityService.getAvailability(emailId, start, end)
        return ResponseEntity.ok(availabilities)
    }

    @GetMapping("/availability/overlap")
    fun findOverlap(
        @RequestParam emailId1: String,
        @RequestParam emailId2: String,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) start: LocalDateTime,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) end: LocalDateTime
    ): ResponseEntity<List<TimeSlot>> {
        val overlap = availabilityService.findOverlap(emailId1, emailId2, start, end)
        return ResponseEntity.ok(overlap)
    }
}