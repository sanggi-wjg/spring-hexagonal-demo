package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

interface UpdateUserByIdUseCase {

    fun updateUserById(command: Command): User

    data class Command(
        val userId: UserId,
        val email: Email?,
        val name: UserName?,
        val userStatus: UserStatus?,
    )
}
