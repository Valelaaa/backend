package com.maib.backend.repository

import com.maib.backend.entity.rating.Rating
import org.springframework.data.jpa.repository.JpaRepository

interface RatingRepository:JpaRepository<Rating,String> {
}