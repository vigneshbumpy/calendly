package com.calendly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Calendly application
 *
 * @constructor Create empty Calendly application
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = ["com.calendly.dao"])
@EnableSwagger2
class CalendlyApplication

/**
 * SpringBoot CalendlyApplication Main
 *
 * @param args
 */
fun main(args: Array<String>) {
    runApplication<CalendlyApplication>(*args)
}