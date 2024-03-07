package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.User

interface FindUserByIdUseCase {

    fun findByUserId(userId: Int): User
}
