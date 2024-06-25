package com.raynor.hexagonal.domain.type.id

@JvmInline
value class ProductId(
    val value: Int
) {
    init {
        require(value > 0) { "ProductId must be greater than zero" }
    }
}
