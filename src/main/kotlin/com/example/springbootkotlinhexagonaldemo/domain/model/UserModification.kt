package com.example.springbootkotlinhexagonaldemo.domain.model

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

data class UserModification(
    val name: UserName? = null,
    val email: Email? = null,
    val userStatus: UserStatus? = null,
)

