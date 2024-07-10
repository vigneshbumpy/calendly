package com.calendly.resource

import com.calendly.manager.AvailabilityManager
import com.calendly.manager.UserManager
import com.calendly.model.User
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping("/api")
class UserResource(private val userManager: UserManager) {

    @PostMapping("/users")
    fun createUser(@RequestParam username: String): ResponseEntity<User> {
        val user = userManager.createUser(username)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/users/{emailId}")
    fun getUser(@PathVariable emailId: String): ResponseEntity<User> {
        val user = userManager.getUser(emailId)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}