package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase

interface ReadUserEndpointPort {

    fun readUsers(query: ReadUsersUseCase.Query): Collection<UserResponseDto>

    fun readUserById(query: ReadUserByIdUseCase.Query): UserDetailResponseDto
}
