package com.raynor.hexagonal.application.port.outbound.persistence

import com.raynor.hexagonal.domain.entity.Mileage

interface WriteMileagePort {

    fun create(mileage: Mileage): Mileage

    fun update(mileage: Mileage): Mileage
}