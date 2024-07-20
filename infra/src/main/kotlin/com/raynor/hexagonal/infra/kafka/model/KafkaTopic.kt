package com.raynor.hexagonal.infra.kafka.model

enum class KafkaTopic(val topic: String) {
    USER_ON_CREATE_V1("user-on-create-v1"),
    USER_ON_CHANGE_MILEAGE_V1("user-on-change-mileage-v1"),
}
