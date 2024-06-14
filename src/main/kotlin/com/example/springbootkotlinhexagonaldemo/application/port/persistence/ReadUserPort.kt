package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface ReadUserPort {

    fun findAll(): Collection<User>

    fun findById(id: UserId): User?

    fun existsByEmail(email: Email): Boolean
}
