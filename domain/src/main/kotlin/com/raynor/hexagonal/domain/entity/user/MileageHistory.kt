package com.raynor.hexagonal.domain.entity.user

import com.raynor.hexagonal.domain.type.common.PositiveOrZeroInt
import com.raynor.hexagonal.domain.type.common.plus
import com.raynor.hexagonal.domain.type.embed.Audit
import com.raynor.hexagonal.domain.type.id.MileageHistoryId

class MileageHistory(
    val id: MileageHistoryId?,
    val beforePoint: PositiveOrZeroInt,
    val afterPoint: PositiveOrZeroInt,
    val point: Int,
    val message: String?,
    val audit: Audit,
    val mileage: Mileage,
) {
    constructor(
        currentPoint: PositiveOrZeroInt,
        inputPoint: Int,
        message: String?,
        mileage: Mileage,
    ) : this(
        id = null,
        beforePoint = currentPoint,
        afterPoint = currentPoint.plus(inputPoint),
        point = inputPoint,
        message = message,
        audit = Audit.now(),
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

    fun id(): MileageHistoryId {
        return this.id!!
    }

    fun textForOnChangeMileageEvent(): String {
        return "${beforePoint.value} -> ${afterPoint.value} 적립금 변경."
    }
}
