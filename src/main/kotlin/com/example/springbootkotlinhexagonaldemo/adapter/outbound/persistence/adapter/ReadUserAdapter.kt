package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.raynor.hexagonal.application.port.outbound.persistence.ReadUserPort
import com.raynor.hexagonal.domain.entity.User
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.id.UserId
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ReadUserAdapter(
    private val userRepository: UserRepository
) : ReadUserPort {

    override fun findAll(
        userIds: List<UserId>?
    ): Collection<User> {
        return userRepository.findAll().map {
            UserMapper.toDomain(it)
        }
    }

    override fun findById(id: UserId): User? {
        return userRepository.findByIdOrNull(id.value)?.let {
            UserMapper.toDomain(it)
        }
    }

    override fun existsByEmail(email: Email): Boolean {
        return userRepository.existsByEmail(email.value)
    }
}