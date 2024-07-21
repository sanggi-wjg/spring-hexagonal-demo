package com.raynor.hexagonal.adapter.outbound.persistence.entity

import com.raynor.hexagonal.domain.enum.UserStatus
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
        protected set

    @Size(max = 254)
    @NotNull
    @Column(name = "email", unique = true, nullable = false, length = 254)
    var email: String = email
        protected set

    @Size(max = 150)
    @NotNull
    @Column(name = "name", nullable = false, length = 150)
    var name: String = name
        protected set

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "user_status", nullable = false, length = 64)
    var userStatus: UserStatus = userStatus
        protected set

    @NotNull
    @Column(name = "created_at", nullable = false)
    var createdAt: Instant = createdAt
        protected set

    @NotNull
    @Column(name = "updated_at", nullable = false)
    var updatedAt: Instant = updatedAt
        protected set

    @NotNull
    @OneToOne
    @JoinColumn(name = "mileage_id")
    var mileage: MileageJPAEntity = mileage
        protected set
}
