package com.maib.backend.entity.rating

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import lombok.EqualsAndHashCode
import java.io.Serializable


@Embeddable
@EqualsAndHashCode
data class UserRatingId(
        @Column(name = "profile_id")
        val profileId: String? = null,

        @Column(name = "rating_id")
        val ratingId: String? = null // Конструкторы, геттеры и сеттеры, переопределение equals и hashCode
) : Serializable

