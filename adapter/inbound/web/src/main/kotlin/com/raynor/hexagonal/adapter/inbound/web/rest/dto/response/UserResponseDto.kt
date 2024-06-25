package com.raynor.hexagonal.adapter.inbound.web.rest.dto.response

import com.fasterxml.jackson.annotation.JsonFormat
import com.raynor.hexagonal.domain.enum.UserStatus
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
