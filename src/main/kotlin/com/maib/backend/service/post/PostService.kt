package com.maib.backend.service.post

import com.maib.backend.entity.category.PostCategories
import com.maib.backend.entity.post.Post
import com.maib.backend.repository.PostRepository
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service

@Service
@NoArgsConstructor
class PostService(
    val postRepository: PostRepository,
) {
    fun getAllPosts(category: String?, sortType: String, sortOrder: String): List<Post> {
        var posts = if (category == null) {
            postRepository.findAll()
        } else {
            val postCategory: PostCategories? = try {
                PostCategories.valueOf(category.uppercase())
            } catch (e: IllegalArgumentException) {
                null
            }
            postRepository.findAll().filter { post: Post? -> post?.category?.categoryName == postCategory?.string }
        }

        posts = when (sortType) {
            "hot" -> if (sortOrder == "asc") posts.sortedBy { it.rating.ratingValue } else posts.sortedByDescending { it.rating.ratingValue }
            "old" -> if (sortOrder == "asc") posts.sortedBy { it.createdDate } else posts.sortedByDescending { it.createdDate }
            "fresh" -> if (sortOrder == "desc") posts.sortedBy { it.createdDate } else posts.sortedByDescending { it.createdDate }
            "title" -> if (sortOrder == "asc") posts.sortedBy { it.title } else posts.sortedByDescending { it.title }
            else -> {
                posts
            }
        }

        return posts
    }

}