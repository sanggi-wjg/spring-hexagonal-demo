package com.example.springbootkotlinhexagonaldemo.domain.type.embed

import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName

data class UserPersonalInfo(
    val email: Email,
    val name: UserName
)
