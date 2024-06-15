package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName

interface CreateUserUseCase {

    fun createUser(command: Command): User

    data class Command(
        val email: Email,
        val name: UserName,
    )
}
