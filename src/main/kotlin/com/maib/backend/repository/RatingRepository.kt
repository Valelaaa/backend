package com.maib.backend.repository

import com.maib.backend.entity.rating.Rating
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RatingRepository : JpaRepository<Rating, String> {


    fun findByComment_CommentId(commentId: String): Optional<Rating>
    fun findByPost_PostId(postId: String): Optional<Rating>
    fun deleteRatingByComment_CommentId(commentId: String)
    fun deleteRatingByPost_PostId(postId: String)
}