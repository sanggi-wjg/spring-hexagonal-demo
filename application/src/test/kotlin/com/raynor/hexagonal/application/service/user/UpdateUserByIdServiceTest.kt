package com.raynor.hexagonal.application.service.user

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.UserFactory
import com.raynor.hexagonal.application.port.inbound.usecase.UpdateUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.embed.UserPersonalInfo
import com.raynor.hexagonal.domain.type.personal.UserName
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    classes = [
        UpdateUserByIdService::class,
    ]
)
class UpdateUserByIdServiceTest(
    private val updateUserByIdService: UpdateUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("유저 정보를 수정할 수 있어야 한다.") {
        // given
        val email = Email("email@dev.com")
        val username = UserName("name")
        val userStatus = UserStatus.LEFT

        val userFixture = UserFactory.create()
        val expectedUser = userFixture.copy(
            personalInfo = UserPersonalInfo(
                email = email,
                name = username,
            ),
            userStatus = userStatus,
        )
        val command = UpdateUserByIdUseCase.Command(
            userId = expectedUser.id!!,
            email = email,
            name = username,
            userStatus = userStatus,
        )

        // mock
        every {
            readUserPort.findById(command.userId)
        } returns userFixture

        every {
            writeUserPort.update(any())
        } returns expectedUser

        // when
        val user = updateUserByIdService.updateUserById(command)

        // then
        user.shouldBeEqualToComparingFields(expectedUser)

        verify(exactly = 1) { readUserPort.findById(command.userId) }
        verify(exactly = 1) { writeUserPort.update(any()) }
    }
})