package com.raynor.hexagonal.adapter.outbound.persistence.repository

import com.querydsl.jpa.impl.JPAQueryFactory
import com.raynor.hexagonal.adapter.outbound.persistence.entity.QUserJPAEntity
import com.raynor.hexagonal.adapter.outbound.persistence.entity.UserJPAEntity
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
