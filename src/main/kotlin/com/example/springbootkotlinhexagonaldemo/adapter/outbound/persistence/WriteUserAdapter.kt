package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository.UserRepository
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
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
