package com.maib.backend.entity.post.dto

import lombok.Data
import java.util.*

@Data
data class PostDto(
        var postId: String = UUID.randomUUID().toString(),

        var title: String = "",
        var description: String? = null,
        var createdDate: Date = Date(System.currentTimeMillis()),
        var rating: Int = 0,
        var isRated: Int = 0,
        var creatorName: String,
        val category: String
)