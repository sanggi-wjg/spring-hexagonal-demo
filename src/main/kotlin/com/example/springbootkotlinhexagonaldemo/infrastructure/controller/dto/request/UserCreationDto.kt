package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.domain.UserCreation


data class UserCreationDto(
    val email: String,
    val name: String,
)

fun UserCreationDto.toDomain() = UserCreation(
    email = email,
    name = name
)