package com.raynor.hexagonal.infra.kafka.model

data class KafkaEvent(
    val topic: KafkaTopic,
    val key: String,
    val message: String,
)
