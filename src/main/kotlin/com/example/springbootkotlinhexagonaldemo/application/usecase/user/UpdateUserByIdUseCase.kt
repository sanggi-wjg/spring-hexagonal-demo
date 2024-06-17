package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName

interface UpdateUserByIdUseCase {

    fun updateUserById(command: Command): User

    data class Command(
        val userId: UserId,
        val email: Email?,
        val name: UserName?,
        val userStatus: UserStatus?,
    )
}
