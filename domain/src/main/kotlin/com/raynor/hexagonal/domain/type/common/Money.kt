package com.raynor.hexagonal.domain.type.common

import java.math.BigDecimal
import java.util.*


data class Money(
    val value: BigDecimal,
    val currency: Currency = Currency.getInstance("KRW")
)
