package com.raynor.hexagonal.infra.kafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.*

@Configuration
@EnableKafka
@EnableConfigurationProperties(KafkaProperty::class)
class KafkaConfig(
    private val kafkaProperty: KafkaProperty,
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        return DefaultKafkaProducerFactory(
            kafkaProperty.producer.properties + mapOf(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.bootstrapServer,
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to kafkaProperty.producer.keySerializer,
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to kafkaProperty.producer.valueSerializer
            )
        )
    }

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        return DefaultKafkaConsumerFactory(
            kafkaProperty.consumer.properties + mapOf(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaProperty.bootstrapServer,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to kafkaProperty.consumer.keyDeserializer,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to kafkaProperty.consumer.valueDeserializer,
                ConsumerConfig.GROUP_ID_CONFIG to KafkaProperty.GROUP_ID,
            )
        )
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory: ConcurrentKafkaListenerContainerFactory<String, String> = ConcurrentKafkaListenerContainerFactory()
        factory.consumerFactory = consumerFactory()
        factory.setConcurrency(1)
        return factory
    }
}