package com.raynor.hexagonal.adapter.outbound.external.producer

import com.raynor.hexagonal.application.port.outbound.external.ProduceEventPort
import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.infra.kafka.KafkaProducerService
import com.raynor.hexagonal.infra.kafka.model.KafkaEvent
import com.raynor.hexagonal.infra.kafka.model.KafkaTopic

class ProduceEventAdapter(
    private val kafkaProducerService: KafkaProducerService,
) : ProduceEventPort {

    override fun onCreate(user: User) {
        kafkaProducerService.produce(
            KafkaEvent(
                KafkaTopic.USER_ON_CREATE_V1,
                user.id().value.toString(),
                user.getEventTextForOnCreate()
            )
        )
    }

    override fun onChangeMileage(user: User) {
        kafkaProducerService.produce(
            KafkaEvent(
                KafkaTopic.USER_ON_CHANGE_MILEAGE_V1,
                user.id().value.toString(),
                user.mileageHistories.last().getEventTextForOnChangeMileage(),
            )
        )
    }
}
