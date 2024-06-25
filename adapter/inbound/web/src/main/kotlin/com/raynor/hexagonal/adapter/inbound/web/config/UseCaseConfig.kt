package com.raynor.hexagonal.adapter.inbound.web.config

import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteMileagePort
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.application.service.user.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfig {

    @Bean
    fun createUserUseCase(
        readUserPort: ReadUserPort,
        writeUserPort: WriteUserPort,
        writeMileagePort: WriteMileagePort
    ) = CreateUserService(
        readUserPort,
        writeUserPort,
        writeMileagePort
    )

    @Bean
    fun readUsesUseCase(
        readUserPort: ReadUserPort
    ) = ReadUsersService(
        readUserPort
    )

    @Bean
    fun readUserByIdUseCase(
        readUserPort: ReadUserPort
    ) = ReadUserByIdService(
        readUserPort
    )

    @Bean
    fun updateUserByIdUseCase(
        readUserPort: ReadUserPort,
        writeUserPort: WriteUserPort,
        writeMileagePort: WriteMileagePort
    ) = UpdateUserByIdService(
        readUserPort,
        writeUserPort,
    )

    @Bean
    fun deleteUserByIdUseCase(
        readUserPort: ReadUserPort,
        writeUserPort: WriteUserPort,
    ) = DeleteUserByIdService(
        readUserPort,
        writeUserPort,
    )
}
