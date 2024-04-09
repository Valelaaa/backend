package com.maib.backend.entity.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonView
import lombok.Data
import java.util.*

data class PostDto(
        var postId: String = UUID.randomUUID().toString(),

        var title: String = "",
        var description: String? = null,
        var createdDate: Date = Date(System.currentTimeMillis()),
        var rating: Int = 0,
        @get:JsonProperty("isRated")
        var isRated: Int = 0,
        var creatorName: String,
        val category: String,
        val commentCount: Int,

        val ratingId:String
)