package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileageHistoryPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileagePort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.AddMileagePointUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class AddMileagePointService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
    private val writeMileagePort: WriteMileagePort,
    private val writeMileageHistoryPort: WriteMileageHistoryPort,
) : AddMileagePointUseCase {

    override fun addMileagePoint(command: AddMileagePointUseCase.Command): User {
        val findUser = readUserPort.findById(command.userId)
        requireNotNull(findUser) {}

        val updatedUser = findUser.putMileagePoint(command.mileagePoint, command.message)
        writeMileagePort.update(updatedUser.mileage)
        writeMileageHistoryPort.create(updatedUser.mileageHistories.last())
        return readUserPort.findById(command.userId)!!
    }
}