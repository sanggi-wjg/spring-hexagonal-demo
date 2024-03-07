package com.example.springbootkotlinhexagonaldemo.domain

import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserModification(
    val name: String? = null,
    val email: String? = null,
    val userStatus: UserStatus? = null,
)

