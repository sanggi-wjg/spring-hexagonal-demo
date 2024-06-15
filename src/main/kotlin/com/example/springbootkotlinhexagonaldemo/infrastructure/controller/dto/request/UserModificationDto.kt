package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.request

import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.domain.type.personal.UserName
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus

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
