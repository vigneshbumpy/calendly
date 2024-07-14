package com.calendly.resource

import com.calendly.model.CalendlyUser
import com.calendly.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * User resource
 *
 * @property userService
 * @constructor Create empty User resource
 */
@RestController
@RequestMapping("/api/users")
class UserResource(private val userService: UserService) {

    /**
     * Create user
     *
     * @param emailId
     * @return User Object
     */
    @PostMapping("/create")
    fun createUser(@RequestParam emailId: String): ResponseEntity<CalendlyUser> {
        var user = userService.getUser(emailId)
        if(user != null) {
            ResponseEntity.notFound()
        }
        user = userService.saveUser(emailId)
        return ResponseEntity.ok(user)
    }

    /**
     * Get user
     *
     * @param emailId
     * @return User Object
     */
    @GetMapping("/get/{emailId}")
    fun getUser(@PathVariable emailId: String): ResponseEntity<CalendlyUser> {
        val user = userService.getUser(emailId)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}