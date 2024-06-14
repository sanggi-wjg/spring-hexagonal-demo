package com.example.springbootkotlinhexagonaldemo.application.usecase

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface FindUserByIdUseCase {

    fun findById(id: UserId): User
}
