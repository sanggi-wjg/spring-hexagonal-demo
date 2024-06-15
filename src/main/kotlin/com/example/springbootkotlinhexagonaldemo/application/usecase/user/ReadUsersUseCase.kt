package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface ReadUsersUseCase {

    fun readUsers(query: Query): Collection<User>

    data class Query(
        val userIds: List<UserId>?,
    )
}
