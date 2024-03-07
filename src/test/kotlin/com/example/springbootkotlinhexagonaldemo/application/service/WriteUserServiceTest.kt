package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.persistence.EntityNotFoundException
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WriteUserServiceTest(
    private val writeUserService: WriteUserService,
    @MockkBean private val readUserPort: ReadUserPort,
    @MockkBean private val writeUserPort: WriteUserPort,
) : FunSpec({

    test("유저를 생성할 수 있어야 한다.") {
        // given
        val userCreation = UserCreation(name = "홍길동", email = "JhZpR@example.com")
        val expected = User(
            id = 0,
            name = userCreation.name,
            email = userCreation.email,
            userStatus = UserStatus.ACTIVE,
        )

        // mock
        every { readUserPort.existsByEmail(any()) } returns false

        every { writeUserPort.saveNewUser(userCreation) } returns expected

        // when
        val user = writeUserService.saveNewUser(userCreation)

        // then
        user.shouldBeEqualToComparingFields(expected)
    }

    test("이메일이 존재하면 에러가 발생해야 한다.") {
        // given
        val userCreation = UserCreation(name = "홍길동", email = "JhZpR@example.com")

        // mock
        every { readUserPort.existsByEmail(any()) } returns true

        // when
        shouldThrow<IllegalArgumentException> {
            writeUserService.saveNewUser(userCreation)
        }.also {
            // then
            it.message shouldBe "User with email ${userCreation.email} already exists"
        }
    }
})