package com.maib.backend.service.post

import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.CreatePostDto
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.entity.rating.Rating
import com.maib.backend.exception.ProfileNotFoundException
import com.maib.backend.exception.category.CategoryNotFoundException
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.CategoryRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.ProfileRepository
import com.maib.backend.repository.RatingRepository
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@NoArgsConstructor
class PostService(
        private val postRepository: PostRepository,
        private val postMapper: PostMapper,
        private val shortPostMapper: ShortPostMapper,
        private val categoryRepository: CategoryRepository,
        private val ratingRepository: RatingRepository,
        private val profileRepository: ProfileRepository,
) {
    fun getAllPosts(category: String?, sortType: String, sortOrder: String): List<ShortPostDto> {
        var postList = postRepository.findAll()
        if (category != null)
            postList = postList.filter { it.category.categoryName == category }


        postList = when (sortType) {
            "title" -> postList.sortedBy { it.title }
            "rating" -> postList.sortedBy { it.rating.ratingValue }
            "date" -> postList.sortedBy { it.createdDate }
            else -> postList
        }

        postList = if (sortOrder == "desc") {
            postList.reversed()
        } else {
            postList
        }


        return postList.map(shortPostMapper::dtoFromEntity)
    }

    fun findById(postId: String): PostDto {
        return postMapper.dtoFromEntity(postRepository.findById(postId).getOrNull()
                ?: throw PostNotFoundException(postId))
    }

    fun createPost(dto: CreatePostDto) {
        val author = profileRepository.findById("111e4567-e89b-12d3-a456-426614174000").getOrNull()
                ?: throw ProfileNotFoundException("111e4567-e89b-12d3-a456-426614174000")

        val category = categoryRepository.findByCategoryName(dto.category.uppercase()).getOrNull()
                ?: throw CategoryNotFoundException(categoryName = dto.category)


        val post = Post(
                postId = dto.postId,
                title = dto.title,
                description = dto.description,
                category = category,
                profile = author,
        )

        val rating = Rating()
        val savedRating = ratingRepository.save(rating)

        println(savedRating.ratingId)
        post.rating = savedRating
        postRepository.save(post)


        category.postCount++
        categoryRepository.save(category)
    }

    fun update(postId: String, postDto: PostDto) {
        val currentPost = postRepository.findById(postId).getOrNull() ?: throw PostNotFoundException(postId)
        val category = postMapper.entityFromDto(postDto).category
        currentPost.title = postDto.title
        currentPost.description = postDto.description
        currentPost.category = category
        postRepository.save(currentPost)
    }

    fun deletePost(postId: String) {
        if (postRepository.existsById(postId)) {
            postRepository.deleteById(postId)
        } else {
            throw PostNotFoundException(postId)
        }
    }

}