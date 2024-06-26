package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface AddMileagePointUseCase {

    fun addMileagePoint(command: Command): User

    data class Command(
        val userId: UserId,
        val mileagePoint: Int,
        val message: String?,
    )
}