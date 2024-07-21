package com.raynor.hexagonal.adapter.outbound.persistence.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull

@Entity
@Table(name = "mileage")
class MileageJPAEntity(
    id: Int?,
    point: Int,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = id
        protected set

    @NotNull
    @Column(name = "point", nullable = false)
    var point: Int = point
        protected set

    @OneToMany(mappedBy = "mileage")
    var mileageHistories: MutableList<MileageHistoryJPAEntity> = mutableListOf()
}
