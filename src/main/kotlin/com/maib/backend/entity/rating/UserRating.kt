package com.maib.backend.entity.rating

import com.maib.backend.entity.profile.Profile
import jakarta.persistence.*
import lombok.EqualsAndHashCode
import lombok.NoArgsConstructor


@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "profiles_ratings")
data class UserRating(
        @EmbeddedId
        val id: UserRatingId? = null,

        @ManyToOne
        @JoinColumn(name = "profile_id", insertable = false, updatable = false)
        val profile: Profile? = null,

        @ManyToOne
        @JoinColumn(name = "rating_id", insertable = false, updatable = false)
        val rating: Rating? = null,

        @Column(name = "rating_value")
        var ratingValue: Int = 0,
)