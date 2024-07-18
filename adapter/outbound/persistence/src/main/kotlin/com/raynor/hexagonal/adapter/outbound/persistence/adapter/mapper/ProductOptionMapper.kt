package com.raynor.hexagonal.adapter.outbound.persistence.adapter.mapper

import com.raynor.hexagonal.adapter.outbound.persistence.entity.ProductOptionJPAEntity
import com.raynor.hexagonal.domain.entity.product.ProductOption
import com.raynor.hexagonal.domain.type.common.Money
import com.raynor.hexagonal.domain.type.common.ProductOptionName
import com.raynor.hexagonal.domain.type.id.ProductOptionId

object ProductOptionMapper {

    fun toDomain(productOption: ProductOptionJPAEntity): ProductOption {
        return ProductOption(
            id = ProductOptionId(productOption.id!!),
            name = ProductOptionName(productOption.name),
            price = Money(productOption.price),
            product = ProductMapper.toDomain(productOption.product),
        )
    }

    fun toJPAEntity(productOption: ProductOption): ProductOptionJPAEntity {
        return com.raynor.hexagonal.adapter.outbound.persistence.entity.ProductOptionJPAEntity(
            id = productOption.id().value,
            name = productOption.name.value,
            price = productOption.price.value,
            product = ProductMapper.toJPAEntity(productOption.product),
        )
    }
}