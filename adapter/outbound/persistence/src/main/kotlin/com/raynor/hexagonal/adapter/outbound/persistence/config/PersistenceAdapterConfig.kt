package com.raynor.hexagonal.adapter.outbound.persistence.config

import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.ReadUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteMileageAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteMileageHistoryAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.WriteUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EnableJpaRepositories(
    basePackages = [
        "com.raynor.hexagonal.adapter.outbound.persistence.repository"
    ]
)
@EntityScan(
    basePackages = [
        "com.raynor.hexagonal.adapter.outbound.persistence.entity"
    ]
)
class PersistenceAdapterConfig {

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
