package com.maib.backend.entity.rating

import com.maib.backend.entity.user.User
import jakarta.persistence.*
import lombok.Builder
import lombok.NoArgsConstructor


@NoArgsConstructor
@Builder

@Entity
@Table(name = "users_rating")
class UserRating(
    @EmbeddedId
    private val id: UserRatingId? = null,

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private val user: User? = null,

    @ManyToOne
    @JoinColumn(name = "rating_id", insertable = false, updatable = false)
    private val rating: Rating? = null,

    @Column(name = "rating_value")
    private val ratingValue:Int = 0,
)