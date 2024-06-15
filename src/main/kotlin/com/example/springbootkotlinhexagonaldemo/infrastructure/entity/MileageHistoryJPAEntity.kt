package com.example.springbootkotlinhexagonaldemo.infrastructure.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.Instant

@Entity
@Table(name = "mileage_history")
class MileageHistoryJPAEntity(
    id: Int?,
    beforePoint: Int,
    afterPoint: Int,
    point: Int,
    message: String?,
    createdAt: Instant,
    updatedAt: Instant,
    mileage: MileageJPAEntity,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Int? = id
        private set

    @NotNull
    @Column(name = "before_point", nullable = false)
    var beforePoint: Int = beforePoint
        private set

    @NotNull
    @Column(name = "after_point", nullable = false)
    var afterPoint: Int = afterPoint
        private set


    @NotNull
    @Column(name = "point", nullable = false)
    var point: Int = point
        private set

    @Size(max = 254)
    @Column(name = "message", length = 254)
    var message: String? = message
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
    @ManyToOne
    @JoinColumn(name = "mileage_id")
    var mileage: MileageJPAEntity = mileage
        private set
}
