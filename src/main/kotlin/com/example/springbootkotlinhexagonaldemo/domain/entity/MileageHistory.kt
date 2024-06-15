package com.example.springbootkotlinhexagonaldemo.domain.entity

import com.example.springbootkotlinhexagonaldemo.domain.type.common.PositiveOrZeroInt
import com.example.springbootkotlinhexagonaldemo.domain.type.common.plus
import com.example.springbootkotlinhexagonaldemo.domain.type.embed.Audit
import com.example.springbootkotlinhexagonaldemo.domain.type.id.MileageHistoryId
import java.time.Instant

class MileageHistory(
    val id: MileageHistoryId?,
    val beforeMileagePoint: PositiveOrZeroInt,
    val afterMileagePoint: PositiveOrZeroInt,
    val point: PositiveOrZeroInt,
    val message: String?,
    val audit: Audit,
    val mileage: Mileage,
) {
    constructor(
        currentPoint: PositiveOrZeroInt,
        adjustPoint: PositiveOrZeroInt,
        message: String?,
        mileage: Mileage,
    ) : this(
        id = null,
        beforeMileagePoint = currentPoint,
        afterMileagePoint = currentPoint.plus(adjustPoint),
        point = adjustPoint,
        message = message,
        audit = Audit(
            createdAt = Instant.now(),
            updatedAt = Instant.now(),
        ),
        mileage = mileage,
    )

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
