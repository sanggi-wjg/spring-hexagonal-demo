package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response

import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.scheme.MileageBasicScheme
import com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.scheme.MileageHistoryBasicScheme
import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
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
    val mileage: MileageBasicScheme,
    val mileageHistories: List<MileageHistoryBasicScheme>,
)
