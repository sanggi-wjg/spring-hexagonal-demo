package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface DeleteUserByIdUseCase {

    fun deleteUserById(id: UserId): Boolean
}