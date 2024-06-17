package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.MileageJPAEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MileageRepository : JpaRepository<MileageJPAEntity, Int>
