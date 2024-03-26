package com.maib.backend.service.post

import com.maib.backend.entity.Mapper
import com.maib.backend.entity.post.Post
import com.maib.backend.entity.post.dto.ShortPostDto
import com.maib.backend.exception.post.PostNotFoundException
import com.maib.backend.repository.PostRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
@RequiredArgsConstructor
class ShortPostMapper(val postRepository: PostRepository) :
        Mapper<Post, ShortPostDto> {

    override fun entityFromDto(dto: ShortPostDto): Post {
        val post: Post = if (postRepository.existsById(dto.postId)) {
            postRepository.findById(dto.postId)
                    .getOrNull() ?: throw PostNotFoundException(dto.postId)
        } else {
            Post()
        }
        return post
    }

    override fun dtoFromEntity(entity: Post): ShortPostDto {

        return ShortPostDto(
                postId = entity.postId,
                postTitle = entity.title,
                postRating = entity.rating.ratingValue,
                creatorName = entity.profile.user.nickname,
                creationDate = entity.createdDate,
                category = entity.category.categoryName,
                isRated = 0,

                )
    }


}