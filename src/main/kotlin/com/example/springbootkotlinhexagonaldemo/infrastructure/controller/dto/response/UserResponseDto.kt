package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
) {

    companion object {
        fun toResponseDto(user: User) = UserResponseDto(
            id = user.id,
            email = user.email,
            name = user.name,
            userStatus = user.userStatus,
        )
    }
}

