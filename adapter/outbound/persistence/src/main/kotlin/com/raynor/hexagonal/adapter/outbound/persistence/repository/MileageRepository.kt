package com.raynor.hexagonal.adapter.outbound.persistence.repository

import com.raynor.hexagonal.adapter.outbound.persistence.entity.MileageJPAEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MileageRepository : JpaRepository<MileageJPAEntity, Int>
