package com.calendly.resource

import com.calendly.model.User
import com.calendly.service.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
@Api(description = "Endpoints for managing user")
class UserResource(private val userService: UserService) {

    @PostMapping("/users")
    @ApiOperation("Create a User")
    fun createUser(@RequestParam emailId: String): ResponseEntity<User> {
        val user = userService.saveUser(emailId)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/users/{emailId}")
    @ApiOperation("Get a User")
    fun getUser(@PathVariable emailId: String): ResponseEntity<User> {
        val user = userService.getUser(emailId)
        return if (user != null) ResponseEntity.ok(user) else ResponseEntity.notFound().build()
    }
}