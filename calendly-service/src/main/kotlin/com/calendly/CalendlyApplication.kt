package com.calendly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Calendly application
 *
 * @constructor Create empty Calendly application
 */


@SpringBootApplication
@EnableAsync
class CalendlyApplication


/**
 * SpringBoot CalendlyApplication Main
 *
 *
 * @param args
 */
fun main(args: Array<String>) {
    val context = runApplication<CalendlyApplication>(*args)
    Runtime.getRuntime().addShutdownHook(Thread {
        println("Application is shutting down")
    })
}
