package com.example.springbootkotlinhexagonaldemo.domain.type.id

data class MileageHistoryId(
    val value: Int
) {
    init {
        require(value > 0) { "MileageHistoryId must be greater than zero" }
    }
}
