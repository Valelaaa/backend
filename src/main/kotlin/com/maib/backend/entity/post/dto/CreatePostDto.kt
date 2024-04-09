package com.maib.backend.entity.post.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class CreatePostDto(
        @JsonProperty("title")
        val title: String = "",

        @JsonProperty("description")
        val description: String? = null,

        @JsonProperty("category")
        val category: String
){
    override fun toString(): String {
        return "CreatePostDto(title='$title', description=$description, category='$category')"
    }
}
