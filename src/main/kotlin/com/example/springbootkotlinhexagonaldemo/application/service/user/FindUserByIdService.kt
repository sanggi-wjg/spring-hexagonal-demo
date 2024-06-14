package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.FindUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class FindUserByIdService(
    private val readUserPort: ReadUserPort,
) : FindUserByIdUseCase {

    override fun findById(id: UserId): User {
        return readUserPort.findById(id)
            ?: throw EntityNotFoundException("user not found: $id")
    }
}