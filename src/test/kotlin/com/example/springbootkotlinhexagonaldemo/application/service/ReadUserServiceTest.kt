package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
import io.mockk.every
import jakarta.persistence.EntityNotFoundException
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUserServiceTest(
    private val readUserService: ReadUserService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    test("전체 유저 조회를 할 수 있어야 한다.") {
        // given
        val expected = listOf(
            User(id = 1, name = "user_1", email = "user_1@dev.com", userStatus = UserStatus.ACTIVE),
            User(id = 2, name = "user_2", email = "user_2@dev.com", userStatus = UserStatus.ACTIVE),
        )

        // mock
        every { readUserPort.findAll() } returns expected

        // when
        val users = readUserService.findAllUsers()

        // then
        users.forEach { user ->
            user.shouldBeEqualToComparingFields(expected.first { user.id == it.id })
        }
    }

    test("존재하지 않는 유저 ID 조회시 에러가 발생하여야 한다.") {
        // given
        val userId = 0

        // mock
        every { readUserPort.findById(any()) } returns null

        // when
        shouldThrow<EntityNotFoundException> {
            readUserService.findByUserId(userId)
        }.also {
            // then
            it.message shouldBe "user not found"
        }
    }

    test("유저 ID 조회할 수 있어야 한다.") {
        // given
        val expected = User(id = 1, name = "user_1", email = "user_1@dev.com", userStatus = UserStatus.ACTIVE)

        // mock
        every { readUserPort.findById(any()) } returns expected

        // when
        val user = readUserService.findByUserId(expected.id)

        // then
        user.shouldBeEqualToComparingFields(expected)
    }
})