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
class ShortPostMapper(val postRepository: PostRepository, val profileRepository: ProfileRepository) :
    PersonalizedMapper<Post, ShortPostDto> {

    override fun entityFromDto(dto: ShortPostDto): Post =
        postRepository.findById(dto.postId)
            .getOrNull() ?: throw PostNotFoundException(dto.postId)


    override fun dtoFromEntity(entity: Post): ShortPostDto {
        return ShortPostDto(
            postId = entity.postId,
            postTitle = entity.title,
            postRating = entity.rating.ratingValue,
            creatorName = entity.author.user.nickname,
            creationDate = entity.createdDate,
            commentsCount = entity.comments.sumOf { it.getCommentCount() },
            isRated = 0
        )
    }

    override fun dtoFromEntityPersonalized(entity: Post, userId: String): ShortPostDto {
        val profile: Profile = profileRepository.findById(userId)
            .getOrNull() ?: throw UserNotFoundException(userId)
        val shortPost = dtoFromEntity(entity)

        val userRating = profile.userRatings?.entries?.firstOrNull { entry ->
            entry.key.post?.postId == entity.postId
        }?.value
        shortPost.isRated = userRating ?: 0

        return shortPost
    }

}