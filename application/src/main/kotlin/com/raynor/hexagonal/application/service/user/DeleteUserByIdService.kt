package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.DeleteUserByIdUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.exception.UserNotFoundException
import org.springframework.transaction.annotation.Transactional

@Transactional
class DeleteUserByIdService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : DeleteUserByIdUseCase {

    override fun deleteUserById(command: DeleteUserByIdUseCase.Command): Boolean {
        val findUser = readUserPort.findById(command.userId)
            ?: throw UserNotFoundException(command.userId)

        writeUserPort.delete(findUser)
        return true
    }
}