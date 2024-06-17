package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.adapter.dto.response

import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class UserResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val createdAt: Instant,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val updatedAt: Instant,
)
