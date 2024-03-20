package com.maib.backend.entity.post.dto

import com.maib.backend.PersonalizedMapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.profile.Profile
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.exception.user.UserNotFoundException
import com.maib.backend.repository.PostRepository
import com.maib.backend.repository.ProfileRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class PostMapper(val postRepository: PostRepository, val profileRepository: ProfileRepository) :
    PersonalizedMapper<Post, PostDto> {
    override fun entityFromDto(dto: PostDto): Post =
        postRepository.findById(dto.postId)
            .getOrNull() ?: throw PostNotFoundException(dto.postId)

    override fun dtoFromEntity(entity: Post): PostDto {
        return PostDto(
            postId = entity.postId,
            title = entity.title,
            description = entity.description,
            createdDate = entity.createdDate,
            creatorName = entity.author.user.nickname,
            isRated = 0,
            rating = entity.rating.ratingValue
        )
    }

    override fun dtoFromEntityPersonalized(entity: Post, userId: String): PostDto {
        val profile: Profile = profileRepository.findById(userId)
            .getOrNull() ?: throw UserNotFoundException(userId)
        val post = dtoFromEntity(entity)

        val userRating = profile.userRatings?.entries?.firstOrNull { entry ->
            entry.key.post?.postId == entity.postId
        }?.value
        post.isRated = userRating ?: 0

        return post
    }
}