package com.raynor.hexagonal.adapter.inbound.web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(
    scanBasePackages = [
        "com.raynor.hexagonal.domain",
        "com.raynor.hexagonal.application",
        "com.raynor.hexagonal.adapter.inbound.web",
        "com.raynor.hexagonal.adapter.outbound.external",
        "com.raynor.hexagonal.adapter.outbound.persistence",
    ],
)
@ComponentScan(
    basePackages = [
//        "com.raynor.hexagonal.adapter.outbound",
//        "com.raynor.hexagonal.adapter.outbound.persistence",
        "com.raynor.hexagonal.adapter.outbound.persistence.repository",
    ]
)
//@ConfigurationPropertiesScan("com.raynor.hexagonal")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
