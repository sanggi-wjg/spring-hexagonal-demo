package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.domain.type.common.Email
import com.example.springbootkotlinhexagonaldemo.domain.type.id.UserId
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

@Adapter
class ReadUserAdapter(
    private val userRepository: UserRepository
) : ReadUserPort {

    override fun findAll(): Collection<User> {
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