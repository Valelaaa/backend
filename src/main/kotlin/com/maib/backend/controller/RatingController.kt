package com.maib.backend.controller

import com.maib.backend.service.rating.RatingService
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/rating")
@RequiredArgsConstructor
class RatingController(
        private val ratingService: RatingService
) {
    @PostMapping("/upvote")
    @ResponseStatus(value = HttpStatus.OK)
    fun upvote(
            @RequestParam("ratingId") ratingId: String
    ) {
        return ratingService.upvote(ratingId)
    }

    @PostMapping("/downvote")
    @ResponseStatus(value = HttpStatus.OK)
    fun downvote(
            @RequestParam("ratingId") ratingId: String
    ) {
        return ratingService.downvote(ratingId)
    }

}