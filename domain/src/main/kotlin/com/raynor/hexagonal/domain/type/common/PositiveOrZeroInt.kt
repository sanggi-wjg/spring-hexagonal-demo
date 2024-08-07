package com.raynor.hexagonal.domain.type.common

@JvmInline
value class PositiveOrZeroInt(
    val value: Int,
) {
    init {
        require(value >= 0) { "PositiveOrZeroInt must be greater than or equal to zero" }
    }
}

fun Int.toPositiveOrZeroInt() = PositiveOrZeroInt(this)

operator fun PositiveOrZeroInt.plus(source: Int) = PositiveOrZeroInt(this.value + source)

operator fun PositiveOrZeroInt.plus(source: PositiveOrZeroInt) = PositiveOrZeroInt(this.value + source.value)
