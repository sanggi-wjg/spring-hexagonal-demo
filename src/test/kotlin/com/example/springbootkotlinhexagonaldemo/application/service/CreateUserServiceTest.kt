package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreateUserServiceTest(
    private val createUserService: CreateUserService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저를 생성할 수 있어야 한다.") {
        // given
        val userCreation = UserCreation(name = UserName("홍길동"), email = Email("JhZpR@example.com"))
        val userMock = User(
            id = UserId(1),
            personalInfo = UserPersonalInfo(name = userCreation.name, email = userCreation.email),
            userStatus = UserStatus.ACTIVE
        )

        // mock
        every {
            readUserPort.existsByEmail(userCreation.email)
        } returns false

        every {
            writeUserPort.create(any())
        } returns userMock

        // when
        val user = createUserService.createUser(userCreation)

        // then
        user.shouldBeEqualToComparingFields(userMock)
    }

    test("존재하는 이메일이라면 에러가 발생해야 한다.") {
        // given
        val userCreation = UserCreation(name = UserName("홍길동"), email = Email("JhZpR@example.com"))

        // mock
        every {
            readUserPort.existsByEmail(userCreation.email)
        } returns true

        // when
        shouldThrow<IllegalArgumentException> {
            createUserService.createUser(userCreation)
        }
    }
})