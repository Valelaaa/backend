package com.maib.backend.service.rating

import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.rating.Rating
import com.maib.backend.exception.comment.CommentNotFoundException
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.exception.rating.RatingNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.RatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class RatingService(
        private val ratingRepository: RatingRepository,
        private val postRepository: PostRepository,
        private val commentRepository: CommentRepository
) {
    fun upvote(entityType: String, entityId: String, userId: String): Rating {
        val entity = when (entityType) {
            "post" -> postRepository.findById(entityId).getOrNull()
                    ?: throw PostNotFoundException("Post not found")

            "comment" -> commentRepository.findById(entityId).getOrNull()
                    ?: CommentNotFoundException("Comment not found")

            else -> throw IllegalArgumentException("Invalid entity type")
        }

        val rating = if (entityId == "post") {
            ratingRepository.findByPost_PostId(entityId).getOrNull()
                    ?: throw RatingNotFoundException(ratingId = entityId)
        } else {
            ratingRepository.findByComment_CommentId(commentId = entityId).getOrNull()
                    ?: throw RatingNotFoundException(ratingId = entityId)
        }


        if (rating != null) {
            if (rating.ratingValue == 1) {
                ratingRepository.delete(rating)
                return rating
            } else {
                rating.ratingValue = 1
                return ratingRepository.save(rating)
            }
        } else {
            val newRating = if (entity is Post) {
                Rating(ratingId = rating.ratingId, comment = null, post = entity, ratingValue = rating.ratingValue + 1)
            } else if (entity is Comment) {
                Rating(ratingId = rating.ratingId, comment = entity, post = null, ratingValue = rating.ratingValue + 1)
            } else {
                throw IllegalStateException()
            }
            return ratingRepository.save(newRating)
        }
    }

    fun downvote(entityType: String, entityId: String, userId: String): Rating {
        val entity = when (entityType) {
            "post" -> postRepository.findById(entityId).getOrNull()
                    ?: throw PostNotFoundException("Post not found")

            "comment" -> commentRepository.findById(entityId).getOrNull()
                    ?: CommentNotFoundException("Comment not found")

            else -> throw IllegalArgumentException("Invalid entity type")
        }

        val rating = if (entityId == "post") {
            ratingRepository.findByPost_PostId(entityId).getOrNull()
                    ?: throw RatingNotFoundException(ratingId = entityId)
        } else {
            ratingRepository.findByComment_CommentId(commentId = entityId).getOrNull()
                    ?: throw RatingNotFoundException(ratingId = entityId)
        }


        if (rating != null) {
            if (rating.ratingValue == -1) {
                ratingRepository.delete(rating)
                return rating
            } else {
                rating.ratingValue = -1
                return ratingRepository.save(rating)
            }
        } else {
            val newRating = if (entity is Post) {
                Rating(ratingId = rating.ratingId, comment = null, post = entity, ratingValue = rating.ratingValue - 1)
            } else if (entity is Comment) {
                Rating(ratingId = rating.ratingId, comment = entity, post = null, ratingValue = rating.ratingValue - 1)
            } else {
                throw IllegalStateException()
            }
            return ratingRepository.save(newRating)
        }
    }
}