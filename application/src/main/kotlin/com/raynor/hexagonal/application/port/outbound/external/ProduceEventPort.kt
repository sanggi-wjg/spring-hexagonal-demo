package com.raynor.hexagonal.application.port.outbound.external

import com.raynor.hexagonal.domain.entity.user.User

interface ProduceEventPort {
    fun onCreate(user: User)
    fun onChangeMileage(user: User)
}