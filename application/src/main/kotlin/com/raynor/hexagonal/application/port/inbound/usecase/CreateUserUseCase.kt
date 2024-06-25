package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.personal.UserName

interface CreateUserUseCase {

    fun createUser(command: Command): User

    data class Command(
        val email: Email,
        val name: UserName,
    )
}
