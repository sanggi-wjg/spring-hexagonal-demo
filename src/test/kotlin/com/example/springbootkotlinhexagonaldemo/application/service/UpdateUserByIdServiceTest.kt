package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.inbound.usecase.UpdateUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.user.UpdateUserByIdService
import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.personal.UserName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
class UpdateUserByIdServiceTest(
    private val updateUserByIdService: UpdateUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저 정보를 수정할 수 있어야 한다.") {
        // given
        val userFixture = UserFactory.create()
        val command = UpdateUserByIdUseCase.Command(
            userId = userFixture.id!!,
            email = Email("email@dev.com"),
            name = UserName("name"),
            userStatus = UserStatus.LEFT,
        )

        val expected = User(
            id = userFixture.id,
            personalInfo = UserPersonalInfo(
                email = command.email!!,
                name = command.name!!,
            ),
            userStatus = command.userStatus!!,
            audit = Audit(
                createdAt = userFixture.audit.createdAt,
                updatedAt = Instant.now(),
            ),
            mileage = userFixture.mileage,
            mileageHistories = userFixture.mileageHistories,
        )

        // mock
        every {
            readUserPort.findById(userFixture.id!!)
        } returns userFixture

        every {
            writeUserPort.update(any())
        } returns expected

        // when
        val user = updateUserByIdService.updateUserById(command)

        // then
        user.shouldBeEqualToComparingFields(expected)
    }
})