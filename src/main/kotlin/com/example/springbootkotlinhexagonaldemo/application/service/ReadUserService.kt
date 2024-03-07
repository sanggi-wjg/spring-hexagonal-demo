package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindAllUsersUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.User
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Transactional(readOnly = true)
@Service
class ReadUserService(
    private val readUserPort: ReadUserPort,
) : FindAllUsersUseCase, FindUserByIdUseCase {

    override fun findAllUsers(): Collection<User> {
        return readUserPort.findAll()
    }

    override fun findByUserId(userId: Int): User {
        return readUserPort.findById(userId)
            ?: throw EntityNotFoundException("user not found")
    }
}
