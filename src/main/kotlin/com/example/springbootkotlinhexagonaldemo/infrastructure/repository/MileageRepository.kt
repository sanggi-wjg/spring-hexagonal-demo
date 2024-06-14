package com.example.springbootkotlinhexagonaldemo.infrastructure.repository

import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.MileageJPAEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MileageRepository : JpaRepository<MileageJPAEntity, Int>
