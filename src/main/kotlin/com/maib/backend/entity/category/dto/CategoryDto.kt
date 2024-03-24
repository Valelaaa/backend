package com.maib.backend.entity.category.dto

import com.maib.backend.entity.post.Post
import jakarta.validation.constraints.NotBlank
import lombok.Data

@Data
data class CategoryDto(
        @NotBlank
        var categoryName: String = "",

        var categoryTitle: String = "",

        var categoryImage: ByteArray? = null,

        var categoryDescription: String = "",

        var tagLine: String = "",

        var postCount: Long = 0,

        var posts: List<Post> = emptyList()
) {
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as CategoryDto

                if (categoryName != other.categoryName) return false
                if (categoryTitle != other.categoryTitle) return false
                if (categoryImage != null) {
                        if (other.categoryImage == null) return false
                        if (!categoryImage.contentEquals(other.categoryImage)) return false
                } else if (other.categoryImage != null) return false
                if (categoryDescription != other.categoryDescription) return false
                if (tagLine != other.tagLine) return false
                if (postCount != other.postCount) return false
                return posts == other.posts
        }

        override fun hashCode(): Int {
                var result = categoryName.hashCode()
                result = 31 * result + categoryTitle.hashCode()
                result = 31 * result + (categoryImage?.contentHashCode() ?: 0)
                result = 31 * result + categoryDescription.hashCode()
                result = 31 * result + tagLine.hashCode()
                result = 31 * result + postCount.hashCode()
                result = 31 * result + posts.hashCode()
                return result
        }
}