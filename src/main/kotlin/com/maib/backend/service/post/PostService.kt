package com.maib.backend.service.post

import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.PostRepository
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@NoArgsConstructor
class PostService(
        private val postRepository: PostRepository,
        private val postMapper: PostMapper,
        private val shortPostMapper: ShortPostMapper
) {
    fun getAllPosts(category: String?, sortType: String, sortOrder: String): List<ShortPostDto> {
        var postList = postRepository.findAll()
        if (category != null)
            postList.filter { it.category.categoryName == category }

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

    fun createPost(postDto: PostDto) {
        val post = postMapper.entityFromDto(postDto)
        postRepository.save(post)
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