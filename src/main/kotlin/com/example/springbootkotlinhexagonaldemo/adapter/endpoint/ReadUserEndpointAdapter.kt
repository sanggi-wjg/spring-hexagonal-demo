package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.ReadUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto
import org.springframework.stereotype.Component

@Component
class ReadUserEndpointAdapter(
    private val findAllUsersUseCase: FindAllUsersUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
) : ReadUserEndpointPort {

    override fun getAllUsers(): Collection<UserResponseDto> {
        return findAllUsersUseCase.findAllUsers().map { UserResponseDto.toResponseDto(it) }
    }

    override fun getUserById(userId: Int): UserResponseDto {
        return findUserByIdUseCase.findByUserId(userId).let {
            UserResponseDto.toResponseDto(it)
        }
    }
}
