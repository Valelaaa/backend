package com.maib.backend.entity.comment

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.util.Date
import java.util.UUID


data class CommentDto(
        @NotNull
        @JsonProperty("commentId")
        var commentId: String = UUID.randomUUID().toString(),
        @NotNull
        @JsonProperty("commentAuthor")

        var commentAuthor: String = "",
        @NotBlank
        @JsonProperty("commentMessage")
        var commentMessage: String = " message",
        @JsonProperty("postId")
        var postId: String?,
        @JsonProperty("createdDate")
        var createdDate: Date = Date(System.currentTimeMillis()),
        @JsonProperty("parentComment")
        var parentCommentId: String? = null,
        @JsonProperty("ratingId")
        var ratingId: String,
        @JsonProperty("rating")
        var rating: Int = 0,
        @get:JsonProperty("isRated")
        var isRated: Int = 0,
        @JsonProperty("subComments")
        var subComments: List<CommentDto>? = null,
)