package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.adapter.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.raynor.hexagonal.application.port.outbound.persistence.WriteUserPort
import com.raynor.hexagonal.domain.entity.User
import org.springframework.stereotype.Component

@Component
class WriteUserAdapter(
    private val userRepository: UserRepository,
) : WriteUserPort {

    override fun create(user: User): User {
        return save(user).let { UserMapper.toDomain(it) }
    }

    override fun update(user: User): User {
        return save(user).let { UserMapper.toDomain(it) }
    }

    override fun delete(user: User) {
        return userRepository.deleteById(user.id!!.value)
    }

    private fun save(user: User): UserJPAEntity {
        val userJPAEntity = UserMapper.toJPAEntity(user)
        return userRepository.save(userJPAEntity)
    }
}
