package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.product.Product

interface ReadProductPort {

    fun findAll(): Collection<Product>
}
