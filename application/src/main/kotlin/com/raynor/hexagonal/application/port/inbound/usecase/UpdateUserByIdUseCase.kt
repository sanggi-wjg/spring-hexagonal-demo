package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.enum.UserStatus
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.id.UserId
import com.raynor.hexagonal.domain.type.personal.UserName

interface UpdateUserByIdUseCase {

    fun updateUserById(command: Command): User

    data class Command(
        val userId: UserId,
        val email: Email?,
        val name: UserName?,
        val userStatus: UserStatus?,
    )
}
