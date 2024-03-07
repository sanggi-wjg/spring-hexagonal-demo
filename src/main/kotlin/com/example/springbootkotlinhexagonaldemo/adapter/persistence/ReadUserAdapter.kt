package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.ReadUserPort
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull

@Adapter
class ReadUserAdapter(
    private val userRepository: UserRepository
) : ReadUserPort {

    override fun findAll(): Collection<User> {
        return userRepository.findAll().map { User.of(it) }
    }

    override fun findById(userId: Int): User? {
        return userRepository.findByIdOrNull(userId)?.let {
            User.of(it)
        }
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }
}