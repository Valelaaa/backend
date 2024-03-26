package com.maib.backend.service.comment

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.comment.Comment
import com.maib.backend.entity.comment.CommentDto
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.profile.Profile
import com.maib.backend.entity.rating.Rating
import com.maib.backend.entity.user.User
import com.maib.backend.exception.comment.CommentNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.RatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class CommentMapper(
        private val commentRepository: CommentRepository,
        private val ratingRepository: RatingRepository
) : Mapper<Comment, CommentDto> {
    override fun entityFromDto(dto: CommentDto): Comment {
        return if (commentRepository.existsById(dto.commentId)) {
            commentRepository.findById(dto.commentId).getOrNull()
                    ?: throw CommentNotFoundException(commentId = dto.commentId)
        } else {
            ///TODO search in profile repository author
            val author = Profile(user = User(nickname = dto.commentAuthor))
            //TODO Search in postRepository
            val rating = Rating()
            val post = Post(postId = dto.postId)

            val parentComment: Comment? = if (dto.parentComment != null) {
                commentRepository.findById(dto.parentComment!!.commentId).getOrNull()
            } else {
                null
            }

            val subComments = mutableListOf<Comment>()
            if (dto.subComments != null && dto.subComments!!.size > 0) {
                dto.subComments!!.forEach {
                    if (commentRepository.existsById(it.commentId))
                        subComments.add(commentRepository.findById(it.commentId).get())
                }
            }
            val comment = Comment(
                    commentId = dto.commentId,
                    message = dto.commentMessage,
                    profile = author,
                    post = post,
                    rating = rating,
                    parent = parentComment,
                    subcomments = subComments
            )
//            rating.comment = comment
            comment.rating = rating

            ratingRepository.save(rating)
            commentRepository.save(comment)

            comment
        }
    }

    override fun dtoFromEntity(entity: Comment): CommentDto {
        val subComments = entity.subcomments.map { dtoFromEntity(it) }

        return CommentDto(
                commentId = entity.commentId,
                commentAuthor = entity.profile.user.nickname,
                commentMessage = entity.message,
                postId = entity.post.postId,
                createdDate = entity.createdDate,
                parentComment = entity.parent?.let { dtoFromEntity(it) },
                ratingId = entity.rating.ratingId,
                rating = entity.rating.ratingValue.toString(),
                subComments = subComments
        )
    }

}
