package com.maib.backend.entity.comment

data class CreateCommentDto(
        var commentMessage: String = " message",
        var postId: String?,
        var parentCommentId: String? = null,
) {
    override fun toString(): String {
        return "CreateCommentDto(commentMessage='$commentMessage', postId=$postId, parentCommentId=$parentCommentId)"
    }
}