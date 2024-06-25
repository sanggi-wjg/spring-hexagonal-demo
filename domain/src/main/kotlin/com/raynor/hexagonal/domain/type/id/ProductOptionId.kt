package com.raynor.hexagonal.domain.type.id

@JvmInline
value class ProductOptionId(
    val value: Int
) {
    init {
        require(value > 0) { "ProductOptionId must be greater than zero" }
    }
}
