package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.ReadUsersUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.domain.entity.user.User
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ReadUsersService(
    private val readUserPort: ReadUserPort,
) : ReadUsersUseCase {

    override fun readUsers(query: ReadUsersUseCase.Query): Collection<User> {
        return readUserPort.findAll(query.userIds)
    }
}