package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.WriteUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase
import org.springframework.stereotype.Component

@Component
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

    override fun updateUserById(command: UpdateUserByIdUseCase.Command): UserResponseDto {
        return updateUserByIdUseCase.updateUserById(command).let {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun deleteUserById(command: DeleteUserByIdUseCase.Command): Boolean {
        return deleteUserByIdUseCase.deleteUserById(command)
    }
}
