package com.maib.backend.entity.post.dto

import lombok.Builder
import lombok.NoArgsConstructor
import java.util.*

@Builder
@NoArgsConstructor
data class ShortPostDto(
    var postId: String,
    var postTitle: String?,
    var postRating: Int? = 0,
    var creatorName: String?,
    var creationDate: Date = Date(System.currentTimeMillis()),
    var isRated: Int = 0,
    var commentsCount: Int = 0,
)