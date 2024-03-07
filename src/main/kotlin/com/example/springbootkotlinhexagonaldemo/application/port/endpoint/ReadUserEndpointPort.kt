package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

interface ReadUserEndpointPort {

    fun getAllUsers(): Collection<UserResponseDto>

    fun getUserById(userId: Int): UserResponseDto
}
