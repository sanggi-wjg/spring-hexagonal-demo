package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.CreateUserUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.exception.ExistsEmailException
import com.raynor.hexagonal.domain.entity.user.User
import org.springframework.transaction.annotation.Transactional

@Transactional
class CreateUserService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
    private val writeMileagePort: WriteMileagePort,
) : CreateUserUseCase {

    override fun createUser(command: CreateUserUseCase.Command): User {
        val isExists = readUserPort.existsByEmail(command.email)
        require(!isExists) {
            throw ExistsEmailException(command.email.value)
        }

        val user = User(name = command.name, email = command.email)
        val mileage = writeMileagePort.create(user.mileage)
        val newUser = user.copy(mileage = mileage)

        return writeUserPort.create(newUser)
    }
}