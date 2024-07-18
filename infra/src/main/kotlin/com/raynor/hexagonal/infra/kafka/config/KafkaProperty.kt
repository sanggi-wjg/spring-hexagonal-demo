package com.raynor.hexagonal.infra.kafka.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.ConfigurationProperties

@ConditionalOnProperty(prefix = "kafka", name = ["bootstrap-server"])
@ConfigurationProperties(prefix = "kafka")
data class KafkaProperty(
    val bootstrapServer: String,
    val producer: Producer,
    val consumer: Consumer
) {
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
