package com.maib.backend.controller

import com.maib.backend.entity.rating.Rating
import com.maib.backend.service.rating.RatingService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
class RatingController(
        private val ratingService: RatingService
) {
    @PostMapping("/{entityType}/{entityId}/upvote")
    @ResponseStatus(value = HttpStatus.OK)
    fun upvote(
            @PathVariable entityType: String,
            @PathVariable entityId: String,
            @RequestBody userId: String
    ): Rating {
       return ratingService.upvote(entityType, entityId, userId)
    }

    @PostMapping("/{entityType}/{entityId}/downvote")
    @ResponseStatus(value = HttpStatus.OK)
    fun downvote(
            @PathVariable entityType: String,
            @PathVariable entityId: String,
            @RequestBody userId: String
    ): Rating {
        return ratingService.downvote(entityType, entityId, userId)
    }

}