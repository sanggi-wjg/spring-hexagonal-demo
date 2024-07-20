package com.raynor.hexagonal.adapter.outbound.external

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.adapter.outbound.external.producer.ProduceEventAdapter
import com.raynor.hexagonal.domain.test.UserFactory
import com.raynor.hexagonal.infra.kafka.KafkaProducerService
import com.raynor.hexagonal.infra.kafka.model.KafkaEvent
import com.raynor.hexagonal.infra.kafka.model.KafkaTopic
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
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
    @MockkBean private val kafkaProducerService: KafkaProducerService
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("[ProduceEventAdapter] 유저 적립금 변경시") {
        // given
        val user = UserFactory.create().putMileagePoint(100)

        // mock
        val event = slot<KafkaEvent>()

        every {
            kafkaProducerService.produce(capture(event))
        } returns Unit

        // when
        produceEventAdapter.onChangeMileage(user)

        // then
        event.captured shouldBe KafkaEvent(
            topic = KafkaTopic.USER_ON_CHANGE_MILEAGE_V1,
            key = "${user.id().value}",
            message = "0 -> 100 적립금 변경."
        )
        verify(exactly = 1) { kafkaProducerService.produce(event.captured) }
    }
})
