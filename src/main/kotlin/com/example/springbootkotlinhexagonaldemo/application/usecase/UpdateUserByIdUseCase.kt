package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserModification

interface UpdateUserByIdUseCase {

    fun updateUserById(userId: Int, userModification: UserModification): User
}