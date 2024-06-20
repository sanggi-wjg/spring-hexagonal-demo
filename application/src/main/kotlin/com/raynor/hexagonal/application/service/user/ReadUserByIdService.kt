package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.ReadUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ReadUserByIdService(
    private val readUserPort: ReadUserPort,
) : ReadUserByIdUseCase {

    override fun readById(query: ReadUserByIdUseCase.Query): User {
        val user = readUserPort.findById(query.userId)
        requireNotNull(user)
        return user
    }
}