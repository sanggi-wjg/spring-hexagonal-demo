package com.raynor.hexagonal.adapter.outbound.persistence.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
@Table(name = "product")
class ProductJPAEntity(
    id: Int?,
    name: String,
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

    @OneToMany(mappedBy = "product")
    var productOptions: MutableList<ProductOptionJPAEntity> = mutableListOf()
}