package com.example.springbootkotlinhexagonaldemo.domain.entity

import com.example.springbootkotlinhexagonaldemo.domain.type.common.PositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageId

class Mileage(
    val id: MileageId?,
    val point: PositiveOrZeroInt,
) {
    constructor(
        point: PositiveOrZeroInt,
    ) : this(
        id = null,
        point = point
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
}
