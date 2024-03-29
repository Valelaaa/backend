package com.maib.backend.entity.rating

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.EqualsAndHashCode
import java.io.Serializable


@Embeddable
@EqualsAndHashCode
class UserRatingId : Serializable {
    @Column(name = "user_id")
    private val userId: String? = null

    @Column(name = "rating_id")
    private val ratingId: String? = null // Конструкторы, геттеры и сеттеры, переопределение equals и hashCode
}

