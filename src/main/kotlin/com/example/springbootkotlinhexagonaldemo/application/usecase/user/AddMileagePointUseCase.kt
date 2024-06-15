package com.example.springbootkotlinhexagonaldemo.application.usecase.user

import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId

interface AddMileagePointUseCase {
    fun addMileagePoint(id: UserId, mileagePoint: Int): User
}