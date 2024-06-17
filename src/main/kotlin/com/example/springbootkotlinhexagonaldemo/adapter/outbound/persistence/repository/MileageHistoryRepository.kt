package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageHistoryJPAEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MileageHistoryRepository : JpaRepository<MileageHistoryJPAEntity, Int>
