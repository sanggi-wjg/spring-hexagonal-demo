package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response

import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
)
