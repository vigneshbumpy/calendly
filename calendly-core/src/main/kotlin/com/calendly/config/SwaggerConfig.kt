package com.calendly.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Swagger config
 *
 * @constructor Create empty Swagger config
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {
    /**
     * Api
     *
     * @return
     */
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.calendly.resource"))
            .paths(PathSelectors.any())
            .build()
    }
}