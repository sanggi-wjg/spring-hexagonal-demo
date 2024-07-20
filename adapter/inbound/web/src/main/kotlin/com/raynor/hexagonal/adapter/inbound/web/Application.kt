package com.raynor.hexagonal.adapter.inbound.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = ["com.raynor.hexagonal"],
    exclude = [
        KafkaAutoConfiguration::class,
    ]
)
@ConfigurationPropertiesScan("com.raynor.hexagonal")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
