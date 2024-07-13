package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.ReadUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.service.exception.UserNotFoundException
import com.raynor.hexagonal.domain.entity.user.User
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
class ReadUserByIdService(
    private val readUserPort: ReadUserPort,
) : ReadUserByIdUseCase {

    override fun readById(query: ReadUserByIdUseCase.Query): User {
        val findUser = readUserPort.findById(query.userId)
        requireNotNull(findUser) { throw UserNotFoundException(query.userId) }

        return findUser
    }
}