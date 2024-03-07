package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification

interface WriteUserPort {

    fun saveNewUser(userCreation: UserCreation): User

    fun updateUserById(userId: Int, userModification: UserModification): User?

    fun deleteUserById(userId: Int)
}