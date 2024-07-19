package com.raynor.hexagonal.application.port.outbound.external

import com.raynor.hexagonal.domain.entity.user.User

interface ProduceEventPort {
    fun onChangeMileage(user: User)
}