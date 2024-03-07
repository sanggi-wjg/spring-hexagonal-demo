package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.application.usecase.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadUserEndpointAdapterTest(
    private val readUserEndpointAdapter: ReadUserEndpointAdapter,
    @MockkBean private val findAllUsersUseCase: FindAllUsersUseCase,
    @MockkBean private val findUserByIdUseCase: FindUserByIdUseCase,
) : FunSpec({

    test("유저들을 조회할 수 있어야 한다.") {
        // given
        val expected = listOf(
            User(id = 1, name = "user_1", email = "user_1@dev.com", userStatus = UserStatus.ACTIVE),
            User(id = 2, name = "user_2", email = "user_2@dev.com", userStatus = UserStatus.ACTIVE),
        )

        // mock
        every { findAllUsersUseCase.findAllUsers() } returns expected

        // when
        val responses = readUserEndpointAdapter.getAllUsers()

        // then
        responses.forEach { response ->
            response.shouldBeEqualToComparingFields(
                UserResponseDto.toResponseDto(expected.first { response.id == it.id })
            )
        }
    }

    test("유저 ID로 조회할 수 있어야 한다.") {
        // given
        val expected = User(id = 1, name = "user_1", email = "user_1@dev.com", userStatus = UserStatus.ACTIVE)

        // mock
        every { findUserByIdUseCase.findByUserId(any()) } returns expected

        // when
        val user = readUserEndpointAdapter.getUserById(1)

        // then
        user.shouldBeEqualToComparingFields(UserResponseDto.toResponseDto(expected))
    }
})
