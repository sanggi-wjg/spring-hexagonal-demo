package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.type.id.UserId

interface ReadUsersUseCase {

    fun readUsers(query: Query): Collection<User>

    data class Query(
        val userIds: List<UserId>?,
    )
}
