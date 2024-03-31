package com.maib.backend.repository

import com.maib.backend.entity.rating.UserRating
import com.maib.backend.entity.rating.UserRatingId
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRatingRepository : JpaRepository<UserRating, UserRatingId> {
    fun findByProfile_ProfileIdAndRating_RatingId(profileId: String, rating: String): Optional<UserRating>
}