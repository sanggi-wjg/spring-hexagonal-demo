package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.rest.dto.request

import com.raynor.hexagonal.application.port.inbound.usecase.UpdateUserByIdUseCase
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName

data class UserModificationRequestDto(
    val email: String? = null,
    val name: String? = null,
    val userStatus: UserStatus? = null,
) {
    fun toUpdateUserByIdUseCaseCommand(userId: Int) = UpdateUserByIdUseCase.Command(
        userId = UserId(userId),
        email = email?.let { Email(it) },
        name = name?.let { UserName(it) },
        userStatus = userStatus
    )
}
