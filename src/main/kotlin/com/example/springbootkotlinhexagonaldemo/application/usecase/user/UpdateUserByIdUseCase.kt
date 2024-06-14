package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface UpdateUserByIdUseCase {

    fun updateUserById(id: UserId, userModification: UserModification): User
}