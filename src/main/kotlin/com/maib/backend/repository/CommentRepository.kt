package com.maib.backend.repository

import com.maib.backend.entity.comment.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, String> {
    fun findCommentsByPost_PostId(postId:String): List<Comment>
}