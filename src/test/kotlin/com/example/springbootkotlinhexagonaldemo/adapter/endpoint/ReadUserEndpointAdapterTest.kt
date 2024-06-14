package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.endpoint.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.UserPersonalInfo
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equality.shouldBeEqualToComparingFields
import io.kotest.matchers.shouldBe
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
        val users = listOf(
            User(
                id = UserId(1),
                personalInfo = UserPersonalInfo(name = UserName("user_11"), email = Email("user_11@dev.com")),
                userStatus = UserStatus.ACTIVE,
            ),
            User(
                id = UserId(2),
                personalInfo = UserPersonalInfo(name = UserName("user_22"), email = Email("user_22@dev.com")),
                userStatus = UserStatus.ACTIVE,
            ),
        )
        val expected = users.map { UserDtoMapper.toUserResponseDto(it) }

        // mock
        every {
            findAllUsersUseCase.findAllUsers()
        } returns users

        // when
        val result = readUserEndpointAdapter.getAllUsers()

        // then
        result shouldBe expected
    }

    test("유저 ID로 조회할 수 있어야 한다.") {
        // given
        val user = User(
            id = UserId(1),
            personalInfo = UserPersonalInfo(name = UserName("user_11"), email = Email("user_11@dev.com")),
            userStatus = UserStatus.ACTIVE,
        )
        val expected = UserDtoMapper.toUserResponseDto(user)

        // mock
        every {
            findUserByIdUseCase.findById(user.id!!)
        } returns user

        // when
        val result = readUserEndpointAdapter.getUserById(user.id!!.value)

        // then
        result.shouldBe(expected)
    }
})
