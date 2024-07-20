package com.raynor.hexagonal.infra.kafka.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties

@ConditionalOnProperty(prefix = "infra.kafka", name = ["bootstrap-server"])
@ConfigurationProperties(prefix = "infra.kafka")
data class KafkaProperty(
    val bootstrapServer: String,
    val producer: Producer,
    val consumer: Consumer
) {
    companion object {
        const val GROUP_ID = "com.raynor.hexagonal"
    }

    data class Producer(
        val keySerializer: String,
        val valueSerializer: String,
        val properties: Map<String, String>,
    )

    data class Consumer(
        val keyDeserializer: String,
        val valueDeserializer: String,
        val properties: Map<String, String>,
    )
}
