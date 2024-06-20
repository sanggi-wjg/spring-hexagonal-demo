package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.MileageHistory

interface WriteMileageHistoryPort {

    fun create(mileageHistory: MileageHistory): MileageHistory
}