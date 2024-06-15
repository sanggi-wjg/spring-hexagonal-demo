package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
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