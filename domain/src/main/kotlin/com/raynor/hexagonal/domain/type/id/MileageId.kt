package com.raynor.hexagonal.domain.type.id

@JvmInline
value class MileageId(
    val value: Int
) {
    init {
        require(value > 0) { "MileageId must be greater than zero" }
    }
}
