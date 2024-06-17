package com.example.springbootkotlinhexagonaldemo.adapter.outbound.persistence.entity

import com.example.springbootkotlinhexagonaldemo.domain.enum.UserStatus
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant

@Entity
@Table(name = "user")
class UserJPAEntity(
    id: Int?,
    email: String,
    name: String,
    userStatus: UserStatus,
    createdAt: Instant,
    updatedAt: Instant,
    mileage: MileageJPAEntity,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = id
        private set

    @Size(max = 254)
    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 254)
    var email: String = email
        private set

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    var name: String = name
        private set

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 64)
    var userStatus: UserStatus = userStatus
        private set

    @NotNull
    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = createdAt
        private set

    @NotNull
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = updatedAt
        private set

    @NotNull
    @OneToOne
    @JoinColumn(name = "mileage_id")
    var mileage: MileageJPAEntity = mileage
        private set
}
