package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.user.MileageHistory

interface WriteMileageHistoryPort {

    fun create(mileageHistory: MileageHistory): MileageHistory
}