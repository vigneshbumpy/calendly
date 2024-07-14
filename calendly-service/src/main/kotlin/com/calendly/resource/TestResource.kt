package com.calendly.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestResource {
    @GetMapping("/healthcheck")
    fun test() = "Hello, World!"
}