package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.UpdateUserByIdUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UpdateUserByIdService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : UpdateUserByIdUseCase {

    override fun updateUserById(command: UpdateUserByIdUseCase.Command): User {
        val findUser = readUserPort.findById(command.userId)
        requireNotNull(findUser)

        return findUser.update(
            inputEmail = command.email,
            inputName = command.name,
            inputUserStatus = command.userStatus
        ).let {
            writeUserPort.update(it)
        }
    }
}