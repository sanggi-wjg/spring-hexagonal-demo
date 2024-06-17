package com.example.springbootkotlinhexagonaldemo.adapter.inbound.endpoint.controller.dto.response.scheme

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.Instant

data class MileageHistoryBasicScheme(
    val id: Int,
    val beforePoint: Int,
    val afterPoint: Int,
    val point: Int,
    val message: String?,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val createdAt: Instant,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ", timezone = "UTC")
    val updatedAt: Instant,
)