package com.example.springbootkotlinhexagonaldemo.domain.type.common

data class PositiveOrZeroInt(
    val value: Int,
) {
    init {
        require(value >= 0) { "PositiveOrZeroInt must be greater than or equal to zero" }
    }
}

fun Int.toPositiveOrZeroInt() = PositiveOrZeroInt(this)
