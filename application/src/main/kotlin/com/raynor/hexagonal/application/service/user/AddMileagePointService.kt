package com.raynor.hexagonal.application.service.user

import com.raynor.hexagonal.application.port.inbound.usecase.AddMileagePointUseCase
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileageHistoryPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.domain.entity.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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
        requireNotNull(findUser)

        val updatedUser = findUser.putMileagePoint(command.mileagePoint, command.message)
        writeMileagePort.update(updatedUser.mileage)
        writeMileageHistoryPort.create(updatedUser.mileageHistories.last())
        return readUserPort.findById(command.userId)!!
    }
}