package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.repository

import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.QUserJPAEntity
import com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity.UserJPAEntity
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
