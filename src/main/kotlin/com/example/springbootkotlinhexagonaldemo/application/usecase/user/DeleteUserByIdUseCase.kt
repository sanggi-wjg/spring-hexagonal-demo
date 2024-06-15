package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface DeleteUserByIdUseCase {

    fun deleteUserById(command: Command): Boolean

    data class Command(
        val userId: UserId,
    )
}