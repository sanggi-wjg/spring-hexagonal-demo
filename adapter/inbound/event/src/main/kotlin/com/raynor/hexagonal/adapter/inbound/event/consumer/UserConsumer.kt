package com.raynor.hexagonal.adapter.inbound.event.consumer

import com.raynor.hexagonal.infra.kafka.config.KafkaProperty
import com.raynor.hexagonal.infra.kafka.model.KafkaTopic
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserConsumer {

    private val logger = LoggerFactory.getLogger(UserConsumer::class.java)

    @KafkaListener(
        topics = [KafkaTopic.USER_ON_CREATE_V1],
        groupId = KafkaProperty.GROUP_ID,
    )
    fun userOnCreate(message: String) {
        logger.info("User created: $message")
    }

    @KafkaListener(
        topics = [KafkaTopic.USER_ON_CHANGE_MILEAGE_V1],
        groupId = KafkaProperty.GROUP_ID,
    )
    fun userOnChangeMileage(message: String) {
        logger.info("User mileage changed: $message")
    }
}
