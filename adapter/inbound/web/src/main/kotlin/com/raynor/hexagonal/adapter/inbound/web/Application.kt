package com.raynor.hexagonal.adapter.inbound.web

import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.raynor.hexagonal.domain",
        "com.raynor.hexagonal.application",
        "com.raynor.hexagonal.adapter.outbound",
        "com.raynor.hexagonal.adapter.inbound.web",
//        "com.raynor.hexagonal.adapter.outbound.external",
//        "com.raynor.hexagonal.adapter.outbound.persistence",
    ],
    scanBasePackageClasses = [
        UserRepository::class,
    ]
)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
