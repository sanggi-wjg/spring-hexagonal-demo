package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.model.UserCreation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreateUserService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : CreateUserUseCase {

    override fun createUser(userCreation: UserCreation): User {
        val isExistsEmail = readUserPort.existsByEmail(userCreation.email)
        require(!isExistsEmail) {}

        return writeUserPort.create(
            User(
                email = userCreation.email,
                name = userCreation.name,
            )
        )
    }
}