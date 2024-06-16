package com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.response

import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.scheme.MileageHistoryScheme
import com.example.springbootkotlinhexagonaldemo.infrastructure.controller.dto.scheme.MileageScheme
import com.example.springbootkotlinhexagonaldemo.infrastructure.enum.UserStatus
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class UserDetailResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val createdAt: Instant,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val updatedAt: Instant,
    val mileage: MileageScheme,
    val mileageHistories: List<MileageHistoryScheme>,
)
