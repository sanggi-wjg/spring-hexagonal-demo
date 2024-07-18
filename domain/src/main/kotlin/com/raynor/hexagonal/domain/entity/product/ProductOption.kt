package com.raynor.hexagonal.domain.entity.product

import com.raynor.hexagonal.domain.type.common.Money
import com.raynor.hexagonal.domain.type.common.ProductOptionName
import com.raynor.hexagonal.domain.type.id.ProductOptionId

class ProductOption(
    val id: ProductOptionId?,
    val name: ProductOptionName,
    val price: Money,
) {
    constructor(
        name: ProductOptionName,
        price: Money,
    ) : this(
        id = null,
        name = name,
        price = price,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProductOption) return false
        if (this.id == null || other.id == null) return false
        return id == (other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun id(): ProductOptionId {
        return this.id!!
    }

    fun copy(
        name: ProductOptionName = this.name,
        price: Money = this.price
    ) = ProductOption(
        id = this.id,
        name = name,
        price = price
    )
}
