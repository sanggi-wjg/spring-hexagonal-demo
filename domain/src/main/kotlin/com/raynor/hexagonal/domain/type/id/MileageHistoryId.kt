package com.raynor.hexagonal.domain.type.id

@JvmInline
value class MileageHistoryId(
    val value: Int
) {
    init {
        require(value > 0) { "MileageHistoryId must be greater than zero" }
    }
}
