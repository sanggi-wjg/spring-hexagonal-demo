package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.mapper.UserDtoMapper
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.UserDetailResponseDto
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.UserResponseDto
import com.example.springbootkotlinhexagonaldemo.application.port.endpoint.ReadUserEndpointPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import org.springframework.stereotype.Component

@Component
class ReadUserEndpointAdapter(
    private val readUsersUseCase: ReadUsersUseCase,
    private val readUserByIdUseCase: ReadUserByIdUseCase,
) : ReadUserEndpointPort {

    override fun readUsers(query: ReadUsersUseCase.Query): Collection<UserResponseDto> {
        return readUsersUseCase.readUsers(query).map {
            UserDtoMapper.toUserResponseDto(it)
        }
    }

    override fun readUserById(query: ReadUserByIdUseCase.Query): UserDetailResponseDto {
        return readUserByIdUseCase.readById(query).let {
            UserDtoMapper.toUserDetailResponseDto(it)
        }
    }
}
