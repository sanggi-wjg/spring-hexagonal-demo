package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

interface WriteUserEndpointPort {

    fun createUser(userCreation: UserCreation): UserResponseDto

    fun modifyUserById(userId: Int, userModification: UserModification): UserResponseDto

    fun removeUserById(userId: Int)
}