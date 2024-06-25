package com.raynor.hexagonal.adapter.inbound.event

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.raynor.hexagonal.domain",
        "com.raynor.hexagonal.application",
        "com.raynor.hexagonal.adapter.inbound.event",
    ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
