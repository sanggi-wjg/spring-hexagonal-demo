package com.raynor.hexagonal.adapter.outbound.persistence.repository

import com.raynor.hexagonal.adapter.outbound.persistence.UserJPAEntityFactory
import com.raynor.hexagonal.adapter.outbound.persistence.config.PersistenceAdapterConfig
import com.raynor.hexagonal.adapter.outbound.persistence.config.QueryDslConfig
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

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
        UserRepository::class,
        MileageRepository::class,
        MileageHistoryRepository::class,
    ]
)
@EnableConfigurationProperties
class UserRepositoryTest(
    private val userRepository: UserRepository,
    private val mileageRepository: MileageRepository,
    private val mileageHistoryRepository: MileageHistoryRepository,
) : FunSpec({

    val userFactory = UserJPAEntityFactory(
        userRepository = userRepository,
        mileageRepository = mileageRepository,
        mileageHistoryRepository = mileageHistoryRepository,
    )

    beforeEach {
        userFactory.deleteAll()
    }

    test("이메일 존재 여부 조회할 수 있어야 한다.") {
        val user = userFactory.create()

        userRepository.existsByEmail(user.email) shouldBe true
        userRepository.existsByEmail("123") shouldBe false
    }
})
