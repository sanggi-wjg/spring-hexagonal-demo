package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserModificationDto(
    val email: String? = null,
    val name: String? = null,
    val userStatus: UserStatus? = null,
)

fun UserModificationDto.toDomain() = UserModification(
    name = name,
    email = email,
    userStatus = userStatus,
)
