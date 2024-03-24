package com.maib.backend.entity.category.dto

import com.maib.backend.entity.post.Post
import jakarta.validation.constraints.NotBlank
import lombok.Data

@Data
data class CategoryDto(
        @NotBlank
        var categoryName: String = "",

        var categoryTitle: String = "",

        var categoryImage: String? = null,

        var categoryDescription: String = "",

        var tagLine: String = "",

        var postCount: Long = 0,

        var posts: List<Post> = emptyList()
)