package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface ReadUserByIdUseCase {

    fun readById(query: Query): User

    data class Query(
        val userId: UserId
    )
}
