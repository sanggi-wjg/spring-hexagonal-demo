package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.entity.User

interface WriteUserPort {

    fun create(user: User): User

    fun update(user: User): User

    fun delete(user: User)
}