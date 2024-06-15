package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response

import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.scheme.MileageHistoryScheme
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.scheme.MileageScheme
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import java.time.Instant

data class UserDetailResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
    val createdAt: Instant,
    val updatedAt: Instant,
    val mileage: MileageScheme,
    val mileageHistories: List<MileageHistoryScheme>,
)
