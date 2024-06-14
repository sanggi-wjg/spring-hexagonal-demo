package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.service.user.FindAllUserService
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
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
            UserFactory.generalUser(),
            UserFactory.generalUser(),
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