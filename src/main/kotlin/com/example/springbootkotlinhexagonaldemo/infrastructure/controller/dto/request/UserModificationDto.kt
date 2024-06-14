package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserModificationDto(
    val email: String? = null,
    val name: String? = null,
    val userStatus: UserStatus? = null,
)

fun UserModificationDto.toDomain() = UserModification(
    name = name?.let { UserName(it) },
    email = email?.let { Email(it) },
    userStatus = userStatus,
)
