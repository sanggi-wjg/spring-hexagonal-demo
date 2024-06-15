package com.example.springbootkotlinhexagonaldemo.adapter.endpoint

import com.example.springbootkotlinhexagonaldemo.adapter.endpoint.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response.UserResponseDto

@Adapter
class WriteUserEndpointAdapter(
    private val createUserUseCase: CreateUserUseCase,
    private val updateUserByIdUseCase: UpdateUserByIdUseCase,
    private val deleteUserByIdUseCase: DeleteUserByIdUseCase,
) : WriteUserEndpointPort {

    override fun createUser(command: CreateUserUseCase.Command): UserResponseDto {
        return createUserUseCase.createUser(command).let {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun modifyUserById(command: UpdateUserByIdUseCase.Command): UserResponseDto {
        return updateUserByIdUseCase.updateUserById(command).let {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun removeUserById(command: DeleteUserByIdUseCase.Command): Boolean {
        return deleteUserByIdUseCase.deleteUserById(command)
    }
}
