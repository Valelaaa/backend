package com.maib.backend.entity.comment

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCommentDto(
        @JsonProperty("commentMessage")
        var commentMessage: String = " message",
        @JsonProperty("postId")
        var postId: String?,
        @JsonProperty("parentCommentId")
        var parentCommentId: String? = null,
) {
    override fun toString(): String {
        return "CreateCommentDto(commentMessage='$commentMessage', postId=$postId, parentCommentId=$parentCommentId)"
    }
}