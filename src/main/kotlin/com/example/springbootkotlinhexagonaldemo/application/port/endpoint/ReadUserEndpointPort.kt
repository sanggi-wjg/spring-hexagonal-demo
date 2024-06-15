package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

interface ReadUserEndpointPort {

    fun readUsers(query: ReadUsersUseCase.Query): Collection<UserResponseDto>

    fun readUserById(query: ReadUserByIdUseCase.Query): UserDetailResponseDto
}
