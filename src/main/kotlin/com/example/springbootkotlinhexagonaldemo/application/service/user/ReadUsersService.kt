package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.ReadUsersUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class ReadUsersService(
    private val readUserPort: ReadUserPort,
) : ReadUsersUseCase {

    override fun readUsers(query: ReadUsersUseCase.Query): Collection<User> {
        return readUserPort.findAll(query.userIds)
    }
}