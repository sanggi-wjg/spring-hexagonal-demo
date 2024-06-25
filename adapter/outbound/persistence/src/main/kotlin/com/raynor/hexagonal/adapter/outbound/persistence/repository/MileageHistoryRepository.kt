package com.raynor.hexagonal.adapter.outbound.persistence.repository

import com.raynor.hexagonal.adapter.outbound.persistence.entity.MileageHistoryJPAEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MileageHistoryRepository : JpaRepository<MileageHistoryJPAEntity, Int>
