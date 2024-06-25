package com.raynor.hexagonal.domain.entity.product

import com.raynor.hexagonal.domain.annotations.RootEntity
import com.raynor.hexagonal.domain.type.common.ProductName
import com.raynor.hexagonal.domain.type.id.ProductId

@RootEntity
class Product(
    val id: ProductId,
    val name: ProductName,
    val productOptions: Set<ProductOption>,
) {
}