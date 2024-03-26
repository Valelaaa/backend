package com.maib.backend.entity.rating

import com.maib.backend.entity.profile.Profile
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor


@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users_rating")
class UserRating(
        @EmbeddedId
        private val id: UserRatingId? = null,

        @ManyToOne
        @JoinColumn(name = "profile_id", insertable = false, updatable = false)
        private val profile: Profile? = null,

        @ManyToOne
        @JoinColumn(name = "rating_id", insertable = false, updatable = false)
        private val rating: Rating? = null,

        @Column(name = "rating_value")
        private val ratingValue: Int = 0,
)