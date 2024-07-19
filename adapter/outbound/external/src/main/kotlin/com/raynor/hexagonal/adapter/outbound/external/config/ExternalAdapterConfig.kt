package com.raynor.hexagonal.adapter.outbound.external.config

import com.raynor.hexagonal.adapter.outbound.external.producer.ProduceEventAdapter
import com.raynor.hexagonal.infra.kafka.KafkaProducerService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(
    basePackages = [
        "com.raynor.hexagonal.infra"
    ]
)
class ExternalAdapterConfig {

    @Bean
    fun produceEventPort(
        kafkaProducerService: KafkaProducerService
    ) = ProduceEventAdapter(kafkaProducerService)
}