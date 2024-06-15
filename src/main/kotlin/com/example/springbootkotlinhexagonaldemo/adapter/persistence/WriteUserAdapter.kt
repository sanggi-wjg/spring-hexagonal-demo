package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.adapter.persistence.mapper.UserMapper
import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.entity.User
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.MileageRepository
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository

@Adapter
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
