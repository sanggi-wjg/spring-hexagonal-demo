package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.user.User

interface WriteUserPort {

    fun create(user: User): User

    fun update(user: User): User

    fun delete(user: User)
}