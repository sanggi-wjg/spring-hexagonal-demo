package com.raynor.hexagonal.adapter.outbound.persistence.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal

@Entity
@Table(name = "product_option")
class ProductOptionJPAEntity(
    id: Int?,
    name: String,
    price: BigDecimal,
    product: ProductJPAEntity,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = id
        private set

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    var name: String = name
        private set

    @NotNull
    @Column(name = "price", nullable = false, precision = 10)
    var price: BigDecimal = price
        private set

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    var product: ProductJPAEntity = product
        private set
}