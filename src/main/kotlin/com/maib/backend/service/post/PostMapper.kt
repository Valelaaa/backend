package com.maib.backend.service.post

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.PostDto
import com.maib.backend.entity.profile.Profile
import com.maib.backend.entity.rating.Rating
import com.maib.backend.exception.category.CategoryNotFoundException
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.CategoryRepository
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.ProfileRepository
import com.maib.backend.repository.RatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class PostMapper(private val postRepository: PostRepository,
                 private val categoryRepository: CategoryRepository,
                 private val ratingRepository: RatingRepository,
                 private val profileRepository: ProfileRepository) :
        Mapper<Post, PostDto> {
    override fun entityFromDto(dto: PostDto): Post {
        return if (postRepository.existsById(dto.postId)) {
            postRepository.findById(dto.postId)
                    .getOrNull() ?: throw PostNotFoundException(dto.postId)
        } else {
            // TODO Add authorisation
            val author = Profile()
            val category = categoryRepository.findByCategoryName(dto.category).getOrNull()
                    ?: throw CategoryNotFoundException(categoryName = dto.category)
            val rating = Rating()
            val post = Post(
                    postId = dto.postId,
                    title = dto.title,
                    description = dto.description,
                    createdDate = dto.createdDate,
                    category = category,
                    comments = listOf(),
                    author = author,
                    rating = rating,
            )

            author.createdPosts = author.createdPosts?.plus(post)
            category.postCount++
            rating.post = post

            profileRepository.save(author)
            ratingRepository.save(rating)
            categoryRepository.save(category)
            post
        }
    }

    override fun dtoFromEntity(entity: Post): PostDto {
        return PostDto(
                postId = entity.postId,
                title = entity.title,
                description = entity.description,
                createdDate = entity.createdDate,
                creatorName = entity.author.user.nickname,
                isRated = 0,
                rating = entity.rating.ratingValue,
                category = entity.category.categoryName
        )
    }

}