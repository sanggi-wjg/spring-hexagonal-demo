package com.raynor.hexagonal.adapter.outbound.external

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.adapter.outbound.external.producer.ProduceEventAdapter
import com.raynor.hexagonal.domain.test.UserFactory
import com.raynor.hexagonal.infra.kafka.KafkaProducerService
import io.kotest.core.spec.style.FunSpec
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(
    value = [
//        KafkaConfig::class,
//        KafkaProperty::class,
    ]
)
@SpringBootTest(
    classes = [
        ProduceEventAdapter::class,
    ]
)
class ProduceEventAdapterTest(
    private val produceEventAdapter: ProduceEventAdapter,
//    private val embeddedKafka: EmbeddedKafka,
    @MockkBean private val kafkaProducerService: KafkaProducerService
) : FunSpec({

    test("[ProduceEventAdapter] 유저 적립금 변경시") {
        // given
        val user = UserFactory.create().putMileagePoint(100)

        // mock
        every {
            kafkaProducerService.produce(any())
        } returns Unit

        // when
        produceEventAdapter.onChangeMileage(user)

        // then
        // todo add test kafka producer service
    }
})
