package com.example.springbootkotlinhexagonaldemo.infrastructure.repository

import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.QUserJPAEntity
import com.example.springbootkotlinhexagonaldemo.infrastructure.entity.UserJPAEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserJPAEntity, Int>, UserRepositoryQueryDslRepository {
    fun existsByEmail(email: String): Boolean
}

interface UserRepositoryQueryDslRepository

class UserRepositoryQueryDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory,
) : UserRepositoryQueryDslRepository {

    private val user = QUserJPAEntity.userJPAEntity
}
