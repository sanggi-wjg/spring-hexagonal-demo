package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName


data class UserCreationDto(
    val email: String,
    val name: String,
)

fun UserCreationDto.toDomain() = UserCreation(
    email = Email(email),
    name = UserName(name),
)