package com.raynor.hexagonal.adapter.inbound.web.rest.dto.response

import com.raynor.hexagonal.domain.enum.UserStatus
import java.time.Instant

data class UserResponseDto(
    val id: Int,
    val email: String,
    val name: String,
    val userStatus: UserStatus,
    val createdAt: Instant,
    val updatedAt: Instant,
)
