package com.example.springbootkotlinhexagonaldemo.domain.entity

import com.example.springbootkotlinhexagonaldemo.domain.type.common.PositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageHistoryId

class MileageHistory(
    val id: MileageHistoryId?,
    val beforeMileagePoint: PositiveOrZeroInt,
    val afterMileagePoint: PositiveOrZeroInt,
    val point: PositiveOrZeroInt,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MileageHistory) return false
        if (this.id == null || other.id == null) return false
        return id == (other.id)
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
