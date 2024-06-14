package com.example.springbootkotlinhexagonaldemo.domain.type.id

data class MileageId(
    val value: Int
) {
    init {
        require(value > 0) { "MileageId must be greater than zero" }
    }
}
