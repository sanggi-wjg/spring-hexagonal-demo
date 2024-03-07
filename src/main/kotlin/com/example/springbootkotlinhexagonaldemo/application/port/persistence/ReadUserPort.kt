package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.User

interface ReadUserPort {

    fun findAll(): Collection<User>

    fun findById(userId: Int): User?

    fun existsByEmail(email: String): Boolean
}
