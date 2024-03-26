package com.maib.backend.entity.post.dto

import java.util.*

data class CreatePostDto(
        var postId:String = UUID.randomUUID().toString(),
        var title: String = "",
        var description: String? = null,
        val category: String
) {
    override fun toString(): String {
        return "CreatePostDto(title='$title', description=$description, category='$category')"
    }
}
