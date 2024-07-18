package com.raynor.hexagonal.domain.entity.product

import com.raynor.hexagonal.domain.annotations.RootEntity
import com.raynor.hexagonal.domain.type.common.ProductName
import com.raynor.hexagonal.domain.type.id.ProductId

@RootEntity
class Product(
    val id: ProductId?,
    val name: ProductName,
    val productOptions: Set<ProductOption>,
) {
    constructor(
        name: ProductName,
    ) : this(
        id = null,
        name = name,
        productOptions = emptySet()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Product) return false
        if (this.id == null || other.id == null) return false
        return id == (other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun id(): ProductId {
        return this.id!!
    }

    fun copy(
        name: ProductName = this.name,
        productOptions: Set<ProductOption> = this.productOptions
    ) = Product(
        id = this.id,
        name = name,
        productOptions = productOptions
    )
}
