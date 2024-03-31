package com.maib.backend.service.post

import com.maib.backend.context.CurrentUserContext
import com.maib.backend.entity.Mapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.exception.rating.RatingNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.ProfileRepository
import com.maib.backend.repository.UserRatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class ShortPostMapper(private val postRepository: PostRepository,
                      private val commentRepository: CommentRepository,
                      private val userRatingRepository: UserRatingRepository,
) :
        Mapper<Post, ShortPostDto> {

    override fun entityFromDto(dto: ShortPostDto): Post {
        val post: Post = if (postRepository.existsById(dto.postId)) {
            postRepository.findById(dto.postId)
                    .getOrNull() ?: throw PostNotFoundException(dto.postId)
        } else {
            Post()
        }
        return post
    }

    override fun dtoFromEntity(entity: Post): ShortPostDto {
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


        return ShortPostDto(
                postId = entity.postId,
                postTitle = entity.title,
                postRating = entity.rating.ratingValue,
                creatorName = entity.profile.user.nickname,
                creationDate = entity.createdDate,
                category = entity.category.categoryName,
                isRated = isRated,
                commentsCount = commentCount
        )
    }


}