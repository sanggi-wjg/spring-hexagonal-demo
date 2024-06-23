package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.UpdateUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.exception.UserNotFoundException
import com.raynor.hexagonal.domain.entity.User
import org.springframework.transaction.annotation.Transactional

@Transactional
class UpdateUserByIdService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : UpdateUserByIdUseCase {

    override fun updateUserById(command: UpdateUserByIdUseCase.Command): User {
        val findUser = readUserPort.findById(command.userId)
        requireNotNull(findUser) { throw UserNotFoundException(command.userId) }

        return findUser.update(
            inputEmail = command.email,
            inputName = command.name,
            inputUserStatus = command.userStatus
        ).let {
            writeUserPort.update(it)
        }
    }
}