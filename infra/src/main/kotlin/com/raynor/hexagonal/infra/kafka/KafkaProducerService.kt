package com.raynor.hexagonal.infra.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.raynor.hexagonal.infra.kafka.config.KafkaConfig
import com.raynor.hexagonal.infra.kafka.model.KafkaEvent
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@ConditionalOnClass(KafkaConfig::class)
@Service
class KafkaProducerService(
    private val kafkaProducer: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
    private val applicationEventPublisher: ApplicationEventPublisher,
) {
    @TransactionalEventListener
    private fun publish(event: KafkaEvent) {
        kafkaProducer.send(
            event.topic,
            event.key,
            objectMapper.writeValueAsString(event.message)
        )
    }

    fun produce(event: KafkaEvent) {
        applicationEventPublisher.publishEvent(event)
    }
}
