package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FindAllUserService(
    private val readUserPort: ReadUserPort,
) : FindAllUsersUseCase {

    override fun findAllUsers(): Collection<User> {
        return readUserPort.findAll()
    }
}