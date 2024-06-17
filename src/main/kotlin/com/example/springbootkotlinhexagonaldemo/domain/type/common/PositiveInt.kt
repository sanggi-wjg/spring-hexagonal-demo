package com.example.springbootkotlinhexagonaldemo.domain.type.common

@JvmInline
value class PositiveInt(
    val value: Int,
) {
    init {
        require(value > 0) { "PositiveInt must be greater than zero" }
    }
}

fun Int.PositiveInt() = PositiveInt(this)
