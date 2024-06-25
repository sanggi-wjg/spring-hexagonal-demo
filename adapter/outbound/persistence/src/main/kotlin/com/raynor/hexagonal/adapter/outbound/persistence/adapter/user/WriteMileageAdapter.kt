package com.raynor.hexagonal.adapter.outbound.persistence.adapter.user

import com.raynor.hexagonal.adapter.outbound.persistence.adapter.mapper.MileageMapper
import com.raynor.hexagonal.adapter.outbound.persistence.entity.MileageJPAEntity
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.domain.entity.user.Mileage

class WriteMileageAdapter(
    private val mileageRepository: MileageRepository,
) : WriteMileagePort {

    override fun create(mileage: Mileage): Mileage {
        return save(mileage).let { MileageMapper.toDomain(it) }
    }

    override fun update(mileage: Mileage): Mileage {
        return save(mileage).let { MileageMapper.toDomain(it) }
    }

    private fun save(mileage: Mileage): MileageJPAEntity {
        val mileageJPAEntity = MileageMapper.toJPAEntity(mileage)
        return mileageRepository.save(mileageJPAEntity)
    }
}