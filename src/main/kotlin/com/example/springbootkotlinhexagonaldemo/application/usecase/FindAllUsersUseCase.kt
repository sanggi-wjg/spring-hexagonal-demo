package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.User

interface FindAllUsersUseCase {

    fun findAllUsers(): Collection<User>
}
