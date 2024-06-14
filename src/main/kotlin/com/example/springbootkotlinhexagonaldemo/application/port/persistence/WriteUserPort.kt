package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface WriteUserPort {

    fun create(user: User): User

    fun update(user: User): User

    fun delete(user: User)
}