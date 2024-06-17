package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.request

import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName

data class UserModificationDto(
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
