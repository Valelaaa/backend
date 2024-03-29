package com.maib.backend.entity.comment.dto

import jakarta.validation.constraints.NotBlank
import lombok.Data
import org.jetbrains.annotations.NotNull
import java.util.*

@Data
data class CommentDto(
        @NotNull
        var commentId: String = UUID.randomUUID().toString(),
        @NotNull
        var commentAuthor: String = "",
        @NotBlank
        var commentMessage: String = " message",
        var postId: String,
        var createdDate: Date = Date(System.currentTimeMillis()),
        var parentComment: CommentDto? = null,
        var ratingId: String,
        var rating: String = "0",
        var subComments: List<CommentDto>? = null,
)
