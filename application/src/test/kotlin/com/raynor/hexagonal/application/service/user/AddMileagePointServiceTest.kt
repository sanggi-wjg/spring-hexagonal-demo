package com.raynor.hexagonal.application.service.user

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.inbound.usecase.AddMileagePointUseCase
import com.raynor.hexagonal.application.port.outbound.external.ProduceEventPort
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileageHistoryPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.domain.entity.user.Mileage
import com.raynor.hexagonal.domain.entity.user.MileageHistory
import com.raynor.hexagonal.domain.test.UserFactory
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.id.MileageHistoryId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        AddMileagePointService::class,
    ]
)
class AddMileagePointServiceTest(
    private val addMileagePointService: AddMileagePointService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeMileagePort: WriteMileagePort,
    @MockkBean private val writeMileageHistoryPort: WriteMileageHistoryPort,
    @MockkBean private val produceEventPort: ProduceEventPort
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("유저 적립금 가감을 할 수 있어야 한다.") {
        // given
        val userFixture = UserFactory.create()
        val command = AddMileagePointUseCase.Command(
            userId = userFixture.id!!,
            mileagePoint = 1234,
            message = "1234 이벤트 당첨",
        )
        val expectedMileage = Mileage(userFixture.mileage.id, 1234.toPositiveOrZeroInt())
        val expectedUser = userFixture.copy(
            mileage = expectedMileage,
            mileageHistories = setOf(
                MileageHistory(
                    id = MileageHistoryId(100),
                    beforePoint = 0.toPositiveOrZeroInt(),
                    afterPoint = 1234.toPositiveOrZeroInt(),
                    point = 1234,
                    message = "1234 이벤트 당첨",
                    audit = userFixture.audit,
                    mileage = expectedMileage,
                ),
            )
        )

        // mock
        every {
            readUserPort.findById(userFixture.id!!)
        } returns expectedUser

        every {
            writeMileagePort.update(any())
        } returns expectedUser.mileage

        every {
            writeMileageHistoryPort.create(any())
        } returns expectedUser.mileageHistories.last()

        every {
            produceEventPort.onChangeMileage(any())
        } returns Unit

        // when
        val updatedUser = addMileagePointService.addMileagePoint(command)

        // then
        updatedUser shouldBeEqualToComparingFields expectedUser

        verify(exactly = 2) { readUserPort.findById(userFixture.id!!) }
        verify(exactly = 1) { writeMileagePort.update(any()) }
        verify(exactly = 1) { writeMileageHistoryPort.create(any()) }
        verify(exactly = 1) { produceEventPort.onChangeMileage(any()) }
    }
})
