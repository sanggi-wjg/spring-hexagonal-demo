package com.example.springbootkotlinhexagonaldemo.adapter.persistence

import com.example.springbootkotlinhexagonaldemo.application.port.persistence.WriteUserPort
import com.example.springbootkotlinhexagonaldemo.domain.User
import com.example.springbootkotlinhexagonaldemo.domain.UserCreation
import com.example.springbootkotlinhexagonaldemo.domain.UserModification
import com.example.springbootkotlinhexagonaldemo.infrastructure.annotations.Adapter
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.repository.UserRepository

@Adapter
class WriteUserAdapter(
    private val userRepository: UserRepository
) : WriteUserPort {

    override fun saveNewUser(userCreation: UserCreation): User {
        return userRepository.save(
            UserEntity(userCreation.email, userCreation.name),
        ).let {
            User.of(it)
        }
    }

    override fun updateUserById(userId: Int, userModification: UserModification): User? {
        return userRepository.findById(userId)
            .map {
                userModification.email?.apply { it.email = this }
                userModification.name?.apply { it.name = this }
                userModification.userStatus?.apply { it.userStatus = this }
                userRepository.save(it)
            }
            .map {
                User.of(it)
            }
            .orElse(null)
    }

    override fun deleteUserById(userId: Int) {
        userRepository.deleteById(userId)
    }
}
