package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User

interface FindAllUsersUseCase {

    fun findAllUsers(): Collection<User>
}
