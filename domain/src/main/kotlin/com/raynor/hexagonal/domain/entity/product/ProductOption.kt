package com.raynor.hexagonal.domain.entity.product

import com.raynor.hexagonal.domain.type.common.Money
import com.raynor.hexagonal.domain.type.common.ProductName
import com.raynor.hexagonal.domain.type.id.ProductOptionId

class ProductOption(
    val id: ProductOptionId,
    val name: ProductName,
    val price: Money,
    val product: Product,
) {
}
