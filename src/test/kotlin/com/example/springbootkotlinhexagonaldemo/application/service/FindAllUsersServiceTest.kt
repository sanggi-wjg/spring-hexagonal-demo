package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FindAllUsersServiceTest(
    private val findAllUserService: FindAllUserService,
    @MockkBean private val readUserPort: ReadUserPort,
) : FunSpec({

    test("전체 유저 조회를 할 수 있어야 한다.") {
        // given
        val expected = listOf(
            User(id = UserId(1), personalInfo = UserPersonalInfo(Email("user_1@dev.com"), UserName("user_11")), userStatus = UserStatus.ACTIVE),
            User(id = UserId(2), personalInfo = UserPersonalInfo(Email("user_2@dev.com"), UserName("user_22")), userStatus = UserStatus.ACTIVE),
        )

        // mock
        every {
            readUserPort.findAll()
        } returns expected

        // when
        val users = findAllUserService.findAllUsers()

        // then
        users.forEach { user ->
            user.shouldBeEqualToComparingFields(expected.first { user.id == it.id })
        }
    }
})