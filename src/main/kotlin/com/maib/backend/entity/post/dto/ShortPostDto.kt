package com.maib.backend.entity.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ShortPostDto(
        var postId: String,
        var postTitle: String?,
        var postRating: Int? = 0,
        var creatorName: String?,
        var creationDate: Date = Date(System.currentTimeMillis()),
        @get:JsonProperty("isRated")
        var isRated: Int = 0,
        var commentsCount: Int = 0,
        var category: String = ""
)