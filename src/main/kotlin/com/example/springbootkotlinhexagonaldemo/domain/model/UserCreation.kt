package com.example.springbootkotlinhexagonaldemo.domain.model

import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName

data class UserCreation(
    val name: UserName,
    val email: Email,
)
