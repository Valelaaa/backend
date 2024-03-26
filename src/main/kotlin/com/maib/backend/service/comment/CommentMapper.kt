package com.maib.backend.service.comment

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.comment.CommentDto
import com.maib.backend.entity.comment.CreateCommentDto
import com.maib.backend.entity.rating.Rating
import com.maib.backend.exception.ProfileNotFoundException
import com.maib.backend.exception.comment.CommentNotFoundException
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.ProfileRepository
import com.maib.backend.repository.RatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class CommentMapper(
        private val commentRepository: CommentRepository,
        private val postsRepository: PostRepository,
        private val ratingRepository: RatingRepository,
        private val profileRepository: ProfileRepository
) : Mapper<Comment, CommentDto> {

    fun createEntityFromDto(dto: CreateCommentDto): Comment {
        val author = profileRepository.findById("111e4567-e89b-12d3-a456-426614174000").getOrNull()
                ?: throw ProfileNotFoundException("111e4567-e89b-12d3-a456-426614174000")

        val post = postsRepository.findById(dto.postId ?: "").getOrNull()
                ?: throw PostNotFoundException(postId = dto.postId ?: "")


        val parentComment: Comment? = if (dto.parentCommentId != null) {
            commentRepository.findById(dto.parentCommentId!!).getOrNull()
        } else {
            null
        }

        val comment = Comment(
                commentId = UUID.randomUUID().toString(),
                message = dto.commentMessage,
                profile = author,
                parent = parentComment,
                subcomments = mutableListOf()
        )

        val rating = Rating()
        val savedRating = ratingRepository.save(rating)
        comment.post = post

        if (dto.parentCommentId != null) {
            parentComment!!.subcomments.add(comment)
            comment.post = null
        }

        comment.rating = savedRating
        return comment
    }

    override fun entityFromDto(dto: CommentDto): Comment {
        return if (commentRepository.existsById(dto.commentId)) {
            commentRepository.findById(dto.commentId).getOrNull()
                    ?: throw CommentNotFoundException(commentId = dto.commentId)
        } else {
            val author = profileRepository.findById("111e4567-e89b-12d3-a456-426614174000").getOrNull()
                    ?: throw ProfileNotFoundException("111e4567-e89b-12d3-a456-426614174000")

            val post = postsRepository.findById(dto.postId ?: "").getOrNull()
                    ?: throw PostNotFoundException(postId = dto.postId ?: "")


            var subComments = mutableListOf<Comment>()
            val parentComment: Comment? = if (dto.parentCommentId != null) {
                commentRepository.findById(dto.parentCommentId!!).getOrNull()
            } else {
                subComments = dto.subComments?.map {
                    it.parentCommentId = dto.commentId
                    entityFromDto(it)
                }?.toMutableList() ?: mutableListOf()
                null
            }

            val comment = Comment(
                    commentId = dto.commentId,
                    message = dto.commentMessage,
                    profile = author,
                    parent = parentComment,
                    subcomments = subComments
            )

            val rating = Rating()
            rating.ratingValue = dto.rating
            val savedRating = ratingRepository.save(rating)
            comment.post = post

            if (dto.parentCommentId != null) {
                parentComment!!.subcomments.add(comment)
                comment.post = null
            }

            comment.rating = savedRating

            comment
        }
    }


    override fun dtoFromEntity(entity: Comment): CommentDto {
        val subComments = entity.subcomments.map { dtoFromEntity(it) }

        return CommentDto(
                commentId = entity.commentId,
                commentAuthor = entity.profile.user.nickname,
                commentMessage = entity.message,
                postId = entity.post?.postId,
                createdDate = entity.createdDate,
                parentCommentId = entity.parent?.commentId,
                ratingId = entity.rating.ratingId,
                rating = entity.rating.ratingValue,
                subComments = subComments,
        )
    }
}
