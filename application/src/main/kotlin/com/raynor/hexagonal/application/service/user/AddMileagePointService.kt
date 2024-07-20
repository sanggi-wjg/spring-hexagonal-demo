package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.AddMileagePointUseCase
import com.raynor.hexagonal.application.port.outbound.external.ProduceEventPort
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileageHistoryPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.service.exception.UserNotFoundException
import com.raynor.hexagonal.domain.entity.user.User
import org.springframework.transaction.annotation.Transactional

@Transactional
class AddMileagePointService(
    private val readUserPort: ReadUserPort,
    private val writeMileagePort: WriteMileagePort,
    private val writeMileageHistoryPort: WriteMileageHistoryPort,
    private val produceEventPort: ProduceEventPort,
) : AddMileagePointUseCase {

    override fun addMileagePoint(command: AddMileagePointUseCase.Command): User {
        val findUser = readUserPort.findById(command.userId)
            ?: throw UserNotFoundException(command.userId)

        val updatedUser = findUser.putMileagePoint(command.mileagePoint, command.message)
        writeMileagePort.update(updatedUser.mileage)
        writeMileageHistoryPort.create(updatedUser.mileageHistories.last())

        produceEventPort.onChangeMileage(updatedUser)
        return readUserPort.findById(command.userId)!!
    }
}
