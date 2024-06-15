package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

interface ReadUserEndpointPort {

    fun getAllUsers(query: ReadUsersUseCase.Query): Collection<UserResponseDto>

    fun getUserById(query: ReadUserByIdUseCase.Query): UserResponseDto
}
