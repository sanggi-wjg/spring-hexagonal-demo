package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage
import com.example.springbootkotlinhexagonaldemo.domain.entity.MileageHistory

interface WriteMileageHistoryPort {

    fun create(mileageHistory: MileageHistory): MileageHistory
}