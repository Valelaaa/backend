package com.maib.backend.service.post

import com.maib.backend.context.CurrentUserContext
import com.maib.backend.entity.Mapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.exception.rating.RatingNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.UserRatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class PostMapper(private val postRepository: PostRepository,
                 private val commentRepository: CommentRepository,
                 private val userRatingRepository: UserRatingRepository) :
        Mapper<Post, PostDto> {

    override fun entityFromDto(dto: PostDto): Post {
        return postRepository.findById(dto.postId)
                .getOrNull() ?: throw PostNotFoundException(dto.postId)
    }

    override fun dtoFromEntity(entity: Post): PostDto {
        val comments = commentRepository.findCommentsByPost_PostId(entity.postId)
        var commentCount = comments.count()
        comments.map {
            it.subcomments.forEach { _ ->
                commentCount += 1
            }
        }

        var currentUser: String? = null
        var isRated = 0
        if (CurrentUserContext.getCurrentUserId() != null) {
            currentUser = CurrentUserContext.getCurrentUserId()
            val userRating = userRatingRepository.findByProfile_ProfileIdAndRating_RatingId(
                    currentUser ?: "",
                    entity.rating.ratingId).orElse(null)
            isRated = userRating?.ratingValue ?: 0
        }


        return PostDto(
                postId = entity.postId,
                title = entity.title,
                description = entity.description,
                createdDate = entity.createdDate,
                creatorName = entity.profile.user.nickname,
                isRated = isRated,
                rating = entity.rating.ratingValue,
                category = entity.category.categoryName,
                commentCount = commentCount
        )
    }

}