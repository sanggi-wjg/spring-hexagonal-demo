package com.example.springbootkotlinhexagonaldemo.application.port.persistence

import com.example.springbootkotlinhexagonaldemo.domain.entity.Mileage

interface WriteMileagePort {

    fun create(mileage: Mileage): Mileage

    fun update(mileage: Mileage): Mileage
}