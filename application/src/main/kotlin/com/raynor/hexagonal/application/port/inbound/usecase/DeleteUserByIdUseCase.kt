package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.type.id.UserId

interface DeleteUserByIdUseCase {

    fun deleteUserById(command: Command): Boolean

    data class Command(
        val userId: UserId,
    )
}