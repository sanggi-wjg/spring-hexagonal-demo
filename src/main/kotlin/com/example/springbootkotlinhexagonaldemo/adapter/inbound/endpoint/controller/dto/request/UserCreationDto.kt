package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import jakarta.validation.constraints.NotBlank


data class UserCreationDto(
    @field:NotBlank()
    val email: String,
    @field:NotBlank()
    val name: String,
) {
    fun toCreateUserUseCaseCommand() = CreateUserUseCase.Command(
        email = Email(email),
        name = UserName(name),
    )
}
