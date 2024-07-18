package com.raynor.hexagonal.adapter.outbound.persistence.adapter.mapper

import com.raynor.hexagonal.adapter.outbound.persistence.entity.ProductJPAEntity
import com.raynor.hexagonal.domain.entity.product.Product
import com.raynor.hexagonal.domain.type.common.ProductName
import com.raynor.hexagonal.domain.type.id.ProductId

object ProductMapper {

    fun toDomain(product: ProductJPAEntity): Product {
        return Product(
            id = ProductId(product.id!!),
            name = ProductName(product.name),
            productOptions = product.productOptions.map { ProductOptionMapper.toDomain(it) }.toSet()
        )
    }

    fun toJPAEntity(product: Product): ProductJPAEntity {
        return ProductJPAEntity(
            id = product.id().value,
            name = product.name.value
        )
    }
}