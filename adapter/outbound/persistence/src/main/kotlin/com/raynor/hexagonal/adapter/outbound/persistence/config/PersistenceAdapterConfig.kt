package com.raynor.hexagonal.adapter.outbound.persistence.config

import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.ReadUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteMileageAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteMileageHistoryAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class PersistenceAdapterConfig(
    private val userRepository: UserRepository
) {

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
