package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.id.UserId

interface ReadUserPort {

    fun findAll(
        userIds: List<UserId>?
    ): Collection<User>

    fun findById(id: UserId): User?

    fun existsByEmail(email: Email): Boolean
}
