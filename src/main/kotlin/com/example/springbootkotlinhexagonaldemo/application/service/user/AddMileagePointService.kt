package com.example.springbootkotlinhexagonaldemo.application.service.user

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileageHistoryPort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteMileagePort
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.application.usecase.user.AddMileagePointUseCase
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

class AddMileagePointService(
    private val readUserPort: ReadUserPort,
    private val writeUserPort: WriteUserPort,
    private val writeMileagePort: WriteMileagePort,
    private val writeMileageHistoryPort: WriteMileageHistoryPort,
) : AddMileagePointUseCase {

    override fun addMileagePoint(id: UserId, mileagePoint: Int): User {
        val user = readUserPort.findById(id)
        requireNotNull(user)

        val newMileageHistory = user.putMileagePoint(mileagePoint, message = "적립금 꽁짜 발급이닷닷")
        writeMileagePort.update(user.mileage)
        user.addMileageHistory(writeMileageHistoryPort.create(newMileageHistory))
        return user
    }
}