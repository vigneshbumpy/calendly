package com.calendly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

fun main() {
    @SpringBootApplication
    class CalendarApplication

    fun main(args: Array<String>) {
        runApplication<CalendarApplication>(*args)
    }
}