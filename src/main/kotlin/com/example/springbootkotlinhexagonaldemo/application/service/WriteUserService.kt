package com.example.springbootkotlinhexagonaldemo.application.service

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.DeleteUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.SaveNewUserUseCase
import com.example.springbootkotlinhexagonaldemo.application.usecase.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class WriteUserService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : SaveNewUserUseCase, UpdateUserByIdUseCase, DeleteUserByIdUseCase {

    override fun saveNewUser(userCreation: UserCreation): User {
        require(!readUserPort.existsByEmail(userCreation.email)) {
            "User with email ${userCreation.email} already exists"
        }
        return writeUserPort.saveNewUser(userCreation)
    }

    override fun updateUserById(userId: Int, userModification: UserModification): User {
        return writeUserPort.updateUserById(userId, userModification)
            ?: throw EntityNotFoundException("User not found")
    }

    override fun deleteUserById(userId: Int) {
        require(readUserPort.findById(userId) != null) {
            "User with id $userId not found"
        }
        writeUserPort.deleteUserById(userId)
    }
}