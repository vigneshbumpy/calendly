package com.calendly.resource

import com.calendly.model.User
import com.calendly.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
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
@Api(description = "Endpoints for managing user")
class UserResource(private val userService: UserService) {

    /**
     * Create user
     *
     * @param emailId
     * @return User Object
     */
    @PostMapping("/create")
    @ApiOperation("Create a User")
    fun createUser(@RequestParam emailId: String): ResponseEntity<User> {
        val user = userService.saveUser(emailId)
        return ResponseEntity.ok(user)
    }

    /**
     * Get user
     *
     * @param emailId
     * @return User Object
     */
    @GetMapping("/get/{emailId}")
    @ApiOperation("Get a User")
    fun getUser(@PathVariable emailId: String): ResponseEntity<User> {
        val user = userService.getUser(emailId)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}