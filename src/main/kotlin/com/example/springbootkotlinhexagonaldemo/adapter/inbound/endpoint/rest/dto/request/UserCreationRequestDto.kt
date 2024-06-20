package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.request

import com.raynor.hexagonal.application.port.inbound.usecase.CreateUserUseCase
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.personal.UserName
import jakarta.validation.constraints.NotBlank


data class UserCreationRequestDto(
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
