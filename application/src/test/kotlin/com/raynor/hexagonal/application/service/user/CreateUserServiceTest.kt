package com.raynor.hexagonal.application.service.user

import com.ninjasquad.springmockk.MockkBean
import com.raynor.hexagonal.application.port.inbound.usecase.CreateUserUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.exception.ExistsEmailException
import com.raynor.hexagonal.domain.entity.user.Mileage
import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.personal.UserName
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.unmockkAll
import io.mockk.verify
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(
    classes = [
        CreateUserService::class
    ]
)
class CreateUserServiceTest(
    private val createUserService: CreateUserService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
    @MockkBean private val writeMileagePort: WriteMileagePort,
) : FunSpec({

    beforeEach {
        unmockkAll()
        clearAllMocks()
    }

    test("[CreateUserServiceTest] 유저 생성할 수 있어야 한다.") {
        // given
        val command = CreateUserUseCase.Command(
            email = Email("JhZpR-1@example.com"),
            name = UserName("홍길동-1"),
        )
        val expectedUser = User(
            email = command.email,
            name = command.name,
        )
        val expectedMileage = Mileage()

        // mock
        every { readUserPort.existsByEmail(command.email) } returns false
        every { writeUserPort.create(any()) } returns expectedUser
        every { writeMileagePort.create(any()) } returns expectedMileage
        // when
        val createdUser = createUserService.createUser(command)

        // then
        createdUser.shouldBeEqualToIgnoringFields(
            expectedUser,
            User::id,
            User::audit,
            User::mileage,
            User::mileageHistories,
        )
        createdUser.mileage.shouldBeEqualToIgnoringFields(
            expectedMileage,
            Mileage::id,
        )

        verify(exactly = 1) { readUserPort.existsByEmail(command.email) }
        verify(exactly = 1) { writeUserPort.create(any()) }
        verify(exactly = 1) { writeMileagePort.create(any()) }
    }

    test("[CreateUserServiceTest] 중복 이메일이 있는 경우 생성할 수 없어야 한다.") {
        // given
        val command = CreateUserUseCase.Command(
            email = Email("JhZpR-1@example.com"),
            name = UserName("홍길동-1"),
        )

        // mock
        every { readUserPort.existsByEmail(command.email) } returns true

        // when ~ then
        shouldThrow<ExistsEmailException> {
            createUserService.createUser(command)
        }

        verify(exactly = 1) { readUserPort.existsByEmail(command.email) }
        verify(exactly = 0) { writeUserPort.create(any()) }
        verify(exactly = 0) { writeMileagePort.create(any()) }
    }
})
