package com.raynor.hexagonal.domain.entity.user

import com.raynor.hexagonal.domain.type.common.PositiveOrZeroInt
import com.raynor.hexagonal.domain.type.common.toPositiveOrZeroInt
import com.raynor.hexagonal.domain.type.id.MileageId

class Mileage(
    val id: MileageId?,
    val point: PositiveOrZeroInt,
) {
    constructor() : this(
        id = null,
        point = 0.toPositiveOrZeroInt()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Mileage) return false
        if (this.id == null || other.id == null) return false
        return id == (other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun copy(
        point: PositiveOrZeroInt = this.point
    ) = Mileage(
        id = this.id,
        point = point
    )

    fun id(): MileageId {
        return this.id!!
    }
}
