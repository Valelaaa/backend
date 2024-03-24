package com.maib.backend.service.comment

import com.maib.backend.entity.comment.dto.CommentDto
import com.maib.backend.exception.comment.CommentNotFoundException
import com.maib.backend.repository.CommentRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CommentService(
        private val commentRepository: CommentRepository,
        private val commentMapper: CommentMapper
) {
    fun findAll(): List<CommentDto> {
        return commentRepository.findAll().map(commentMapper::dtoFromEntity)
    }

    fun findById(commentId: String): CommentDto {
        return commentMapper.dtoFromEntity(
                commentRepository.findById(commentId).getOrNull()
                        ?: throw CommentNotFoundException(commentId))
    }

    fun deleteById(commentId: String) {
        commentRepository.deleteById(commentId)
    }

    fun create(commentDto: CommentDto) {
        val comment = commentMapper.entityFromDto(commentDto)
        commentRepository.save(comment)
    }

    fun update(commentId: String, commentDto: CommentDto) {
        val currentComment = commentRepository.findById(commentId).getOrNull()
                ?: throw CommentNotFoundException(commentId)

        val subComments = commentDto.subComments?.map(commentMapper::entityFromDto)

        currentComment.message = commentDto.commentMessage
        currentComment.rating.ratingValue = Integer.parseInt(commentDto.rating)
        currentComment.subComments = subComments
        commentRepository.save(currentComment)
    }

}
