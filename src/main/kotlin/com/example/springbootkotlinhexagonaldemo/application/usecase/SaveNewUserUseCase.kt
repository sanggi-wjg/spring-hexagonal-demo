package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation

interface SaveNewUserUseCase {

    fun saveNewUser(userCreation: UserCreation): User
}