package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.DeleteUserByIdUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class DeleteUserByIdService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
) : DeleteUserByIdUseCase {

    override fun deleteUserById(command: DeleteUserByIdUseCase.Command): Boolean {
        val findUser = readUserPort.findById(command.userId)
        requireNotNull(findUser)

        writeUserPort.delete(findUser)
        return true
    }
}