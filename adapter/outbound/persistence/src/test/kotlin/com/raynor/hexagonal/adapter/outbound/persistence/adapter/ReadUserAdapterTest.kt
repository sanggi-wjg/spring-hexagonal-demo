package com.raynor.hexagonal.adapter.outbound.persistence.adapter

import com.raynor.hexagonal.adapter.outbound.persistence.UserJPAEntityFactory
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.mapper.UserMapper
import com.raynor.hexagonal.adapter.outbound.persistence.adapter.user.ReadUserAdapter
import com.raynor.hexagonal.adapter.outbound.persistence.config.PersistenceAdapterConfig
import com.raynor.hexagonal.adapter.outbound.persistence.config.QueryDslConfig
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageHistoryRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.MileageRepository
import com.raynor.hexagonal.adapter.outbound.persistence.repository.UserRepository
import com.raynor.hexagonal.domain.type.common.Email
import com.raynor.hexagonal.domain.type.id.UserId
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.transaction.support.TransactionTemplate

@Import(
    value = [
        DataSourceAutoConfiguration::class,
        HibernateJpaAutoConfiguration::class,
        TransactionAutoConfiguration::class,
        PersistenceAdapterConfig::class,
        QueryDslConfig::class,
    ]
)
@SpringBootTest(
    classes = [
        ReadUserAdapter::class,
    ]
)
class ReadUserAdapterTest(
    private val readUserAdapter: ReadUserAdapter,
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val mileageHistoryRepository: MileageHistoryRepository,
    private val transactionTemplate: TransactionTemplate,
) : FunSpec({

    val userFactory = UserJPAEntityFactory(
        userRepository = userRepository,
        mileageRepository = mileageRepository,
        mileageHistoryRepository = mileageHistoryRepository,
    )

    beforeEach {
        userFactory.deleteAll()
    }

    test("123") {
        val userEntities = (1..3).map { userFactory.create() }
        val firstUserEntity = userEntities.first()
        val firstUserId = UserId(firstUserEntity.id!!)
        val firstUserEmail = Email(firstUserEntity.email)

        transactionTemplate.executeWithoutResult {
            readUserAdapter.findAll().shouldBe(
                userEntities.map { UserMapper.toDomain(it) }
            )

            readUserAdapter.findById(firstUserId).shouldBe(
                UserMapper.toDomain(firstUserEntity)
            )

            readUserAdapter.existsByEmail(firstUserEmail).shouldBe(
                true
            )
        }
    }
})