package com.raynor.hexagonal.infra.kafka.model

data class KafkaEvent(
    val topic: String,
    val key: String,
    val message: String,
)
