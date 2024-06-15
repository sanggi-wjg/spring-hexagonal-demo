package com.example.springbootkotlinhexagonaldemo.domain.entity

import com.example.springbootkotlinhexagonaldemo.domain.type.common.PositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.common.plus
import com.example.springbootkotlinhexagonaldemo.domain.type.common.toPositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageId

class Mileage(
    val id: MileageId?,
    var point: PositiveOrZeroInt,
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
}
