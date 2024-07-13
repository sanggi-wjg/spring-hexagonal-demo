package com.raynor.hexagonal.application.port.inbound.usecase

import com.raynor.hexagonal.domain.entity.user.User
import com.raynor.hexagonal.domain.type.id.UserId

interface AddMileagePointUseCase {

    fun addMileagePoint(command: Command): User

    data class Command(
        val userId: UserId,
        val mileagePoint: Int,
        val message: String?,
    )
}