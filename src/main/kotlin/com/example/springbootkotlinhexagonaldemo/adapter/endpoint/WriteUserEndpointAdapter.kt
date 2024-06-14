package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.endpoint.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.model.UserModification
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

@Adapter
class WriteUserEndpointAdapter(
    private val saveNewUserUseCase: CreateUserUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
) : WriteUserEndpointPort {

    override fun createUser(userCreation: UserCreation): UserResponseDto {
        return saveNewUserUseCase.createUser(userCreation).let {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun modifyUserById(userId: Int, userModification: UserModification): UserResponseDto {
        return updateUserByIdUseCase.updateUserById(UserId(userId), userModification).let {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun removeUserById(userId: Int): Boolean {
        deleteUserByIdUseCase.deleteUserById(UserId(userId))
        return true
    }
}
