package com.example.springbootkotlinhexagonaldemo.application.port.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase

interface WriteUserEndpointPort {

    fun createUser(command: CreateUserUseCase.Command): UserResponseDto

    fun updateUserById(command: UpdateUserByIdUseCase.Command): UserResponseDto

    fun deleteUserById(command: DeleteUserByIdUseCase.Command): Boolean
}