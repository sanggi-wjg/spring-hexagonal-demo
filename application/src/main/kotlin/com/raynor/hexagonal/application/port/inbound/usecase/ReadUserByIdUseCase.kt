package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.type.id.UserId

interface ReadUserByIdUseCase {

    fun readById(query: Query): User

    data class Query(
        val userId: UserId
    )
}
