package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.SaveNewUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

@Adapter
class WriteUserEndpointAdapter(
    private val saveNewUserUseCase: SaveNewUserUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
) : WriteUserEndpointPort {

    override fun createUser(userCreation: UserCreation): UserResponseDto {
        return saveNewUserUseCase.saveNewUser(userCreation).let {
            UserResponseDto.toResponseDto(it)
        }
    }

    override fun modifyUserById(userId: Int, userModification: UserModification): UserResponseDto {
        return updateUserByIdUseCase.updateUserById(userId, userModification).let {
            UserResponseDto.toResponseDto(it)
        }
    }

    override fun removeUserById(userId: Int) {
        deleteUserByIdUseCase.deleteUserById(userId)
    }
}