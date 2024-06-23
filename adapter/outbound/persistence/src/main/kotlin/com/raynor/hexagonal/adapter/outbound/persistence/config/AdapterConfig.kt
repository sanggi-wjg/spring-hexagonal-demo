package com.raynor.hexagonal.adapter.outbound.persistence.config

import com.raynor.hexagonal.adapter.outbound.persistence.adapter.ReadUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.WriteMileageAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.WriteMileageHistoryAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.WriteUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class AdapterConfig {

    @Bean
    fun writeUserPort(
        userRepository: UserRepository
    ) = WriteUserAdapter(
        userRepository
    )

    @Bean
    fun readUserPort(
        userRepository: UserRepository
    ) = ReadUserAdapter(
        userRepository
    )

    @Bean
    fun writeMileagePort(
        mileageRepository: MileageRepository
    ) = WriteMileageAdapter(
        mileageRepository
    )

    @Bean
    fun writeMileageHistoryPort(
        mileageHistoryRepository: MileageHistoryRepository
    ) = WriteMileageHistoryAdapter(
        mileageHistoryRepository
    )
}
