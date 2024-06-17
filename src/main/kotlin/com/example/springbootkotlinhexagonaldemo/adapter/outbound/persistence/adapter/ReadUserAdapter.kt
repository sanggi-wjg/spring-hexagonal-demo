package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
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