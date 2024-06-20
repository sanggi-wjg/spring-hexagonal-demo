package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.CreateUserUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class CreateUserService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
    private val writeMileagePort: WriteMileagePort,
) : CreateUserUseCase {

    override fun createUser(command: CreateUserUseCase.Command): User {
        val isExistsEmail = readUserPort.existsByEmail(command.email)
        require(!isExistsEmail)

        val user = User(name = command.name, email = command.email)
        val mileage = writeMileagePort.create(user.mileage)

        return writeUserPort.create(
            user.copy(mileage = mileage)
        )
    }
}