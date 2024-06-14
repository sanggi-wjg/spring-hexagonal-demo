package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
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
class FindUserByIdServiceTest(
    private val findUserByIdService: FindUserByIdService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    test("존재하지 않는 유저 ID 조회시 에러가 발생하여야 한다.") {
        // given
        val userId = UserId(1)

        // mock
        every {
            readUserPort.findById(userId)
        } returns null

        // when
        shouldThrow<EntityNotFoundException> {
            findUserByIdService.findById(userId)
        }
    }

    test("유저 ID 조회할 수 있어야 한다.") {
        // given
        val expected = UserFactory.generalUser()

        // mock
        every {
            readUserPort.findById(expected.id!!)
        } returns expected

        // when
        val user = findUserByIdService.findById(expected.id!!)

        // then
        user.shouldBeEqualToComparingFields(expected)
    }
})