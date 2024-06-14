package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation

interface CreateUserUseCase {

    fun createUser(userCreation: UserCreation): User
}