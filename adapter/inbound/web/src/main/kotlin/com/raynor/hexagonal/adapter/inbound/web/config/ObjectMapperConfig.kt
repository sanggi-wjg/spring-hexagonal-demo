package com.raynor.hexagonal.adapter.inbound.web.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class ObjectMapperConfig {

    @Primary
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper()
            .registerModules(JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_WITH_ZONE_ID, true)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }
}