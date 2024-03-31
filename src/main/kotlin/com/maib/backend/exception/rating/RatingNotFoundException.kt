package com.maib.backend.exception.rating

import com.maib.backend.exception.NotFoundException

class RatingNotFoundException(ratingId: String, message: String = "Rating with id = $ratingId Not Found") : NotFoundException(message) {

}
