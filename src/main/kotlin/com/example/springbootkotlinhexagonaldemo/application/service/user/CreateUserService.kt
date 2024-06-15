package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileagePort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.CreateUserUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
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