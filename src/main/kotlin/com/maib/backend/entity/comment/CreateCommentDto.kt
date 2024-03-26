package com.maib.backend.entity.comment

data class CreateCommentDto(
        var commentMessage: String = " message",
        var postId: String?,
        var parentCommentId: String? = null,
)