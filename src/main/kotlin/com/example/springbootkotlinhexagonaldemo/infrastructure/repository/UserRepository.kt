package com.example.springbootkotlinhexagonaldemo.infrastructure.repository

import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.QUserEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int>, UserRepositoryQueryDslRepository {
    fun existsByEmail(email: String): Boolean
}

interface UserRepositoryQueryDslRepository

class UserRepositoryQueryDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserRepositoryQueryDslRepository {
    private val user = QUserEntity.userEntity
}
