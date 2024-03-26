package com.maib.backend.service.comment

import com.maib.backend.entity.comment.CommentDto
import com.maib.backend.entity.comment.CreateCommentDto
import com.maib.backend.exception.comment.CommentNotFoundException
import com.maib.backend.repository.CommentRepository
import com.maib.backend.repository.RatingRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CommentService(
        private val commentRepository: CommentRepository,
        private val commentMapper: CommentMapper,
        private val ratingRepository: RatingRepository
) {
    fun findById(commentId: String): CommentDto {
        return commentMapper.dtoFromEntity(
                commentRepository.findById(commentId).getOrNull()
                        ?: throw CommentNotFoundException(commentId))
    }

    fun deleteById(commentId: String) {
        commentRepository.deleteById(commentId)

    }

    fun create(commentDto: CreateCommentDto) {
        val comment = commentMapper.createEntityFromDto(commentDto)
        commentRepository.save(comment)
    }

    fun update(commentId: String, commentDto: CommentDto) {
        val currentComment = commentRepository.findById(commentId).getOrNull()
                ?: throw CommentNotFoundException(commentId)

        val subComments = commentDto.subComments?.map(commentMapper::entityFromDto)

        currentComment.message = commentDto.commentMessage
        currentComment.rating.ratingValue = commentDto.rating
        currentComment.subcomments = subComments?.toMutableList() ?: mutableListOf()
        commentRepository.save(currentComment)
    }

    fun findCommentsByPostId(postId: String, sortType: String?, sortOrder: String?): List<CommentDto> {
        val comments = commentRepository.findCommentsByPost_PostId(postId).map(commentMapper::dtoFromEntity)
        if (sortType.isNullOrBlank() || sortOrder.isNullOrBlank()) {
            return comments
        }
        val comparator = when (sortType) {
            "date" -> compareBy<CommentDto> { it.createdDate }.reversed()
            "rating" -> compareBy { it.rating }
            else -> throw IllegalStateException("Invalid sort type")
        }

        return when (sortOrder) {
            "asc" -> comments.sortedWith(comparator)
            "desc" -> comments.sortedWith(comparator.reversed())
            else -> throw IllegalStateException("Invalid sort order")
        }
    }


}
