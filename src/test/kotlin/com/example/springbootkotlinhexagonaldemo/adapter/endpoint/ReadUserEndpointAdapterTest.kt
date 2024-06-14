package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.endpoint.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.factory.UserFactory
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
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
            UserFactory.generalUser(),
            UserFactory.generalUser(),
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
        val user = UserFactory.generalUser()
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
