package com.raynor.hexagonal.adapter.inbound.web.rest.dto.response.scheme

import java.time.Instant

data class MileageHistoryBasicScheme(
    val id: Int,
    val beforePoint: Int,
    val afterPoint: Int,
    val point: Int,
    val message: String?,
    val createdAt: Instant,
    val updatedAt: Instant,
)