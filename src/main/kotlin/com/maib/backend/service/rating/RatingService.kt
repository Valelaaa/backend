package com.maib.backend.service.rating

import com.maib.backend.context.CurrentUserContext
import com.maib.backend.entity.rating.UserRating
import com.maib.backend.entity.rating.UserRatingId
import com.maib.backend.exception.ProfileNotFoundException
import com.maib.backend.exception.rating.RatingNotFoundException
import com.maib.backend.repository.ProfileRepository
import com.maib.backend.repository.RatingRepository
import com.maib.backend.repository.UserRatingRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class RatingService(
        private val ratingRepository: RatingRepository,
        private val userRatingRepository: UserRatingRepository,
        private val profileRepository: ProfileRepository
) {
    fun upvote(ratingId: String) {
        val currentUser = CurrentUserContext.getCurrentUserId() ?: return

        val userRating = userRatingRepository.findByProfile_ProfileIdAndRating_RatingId(currentUser, ratingId)
                .orElseGet {
                    val profile = profileRepository.findById(currentUser)
                            .orElseThrow { ProfileNotFoundException(s = currentUser) }

                    UserRating(
                            id = UserRatingId(profileId = profile.profileId, ratingId = ratingId),
                            ratingValue = 0 // Начальное значение голоса пользователя
                    )
                }

        val rating = ratingRepository.findById(ratingId)
                .orElseThrow { RatingNotFoundException(ratingId = ratingId) }

        val previousRatingValue = userRating.ratingValue

        // Если пользователь уже голосовал положительно, не меняем значения
        if (previousRatingValue > 0) {
            userRating.ratingValue = 0
            rating.ratingValue -= 1
            ratingRepository.save(rating)
            userRatingRepository.save(userRating)
            return
        }

        // Если предыдущий голос был отрицательным, то значение голоса увеличивается на 2, иначе на 1
        userRating.ratingValue = 1

        // Увеличиваем рейтинг на 1 или 2 в зависимости от предыдущего голоса пользователя
        rating.ratingValue += if (previousRatingValue < 0) 2 else 1

        // Сохраняем или обновляем записи в репозиториях
        ratingRepository.save(rating)
        userRatingRepository.save(userRating)
    }


    fun downvote(ratingId: String) {
        val currentUser = CurrentUserContext.getCurrentUserId() ?: return

        val userRating = userRatingRepository.findByProfile_ProfileIdAndRating_RatingId(currentUser, ratingId)
                .orElseGet {
                    val profile = profileRepository.findById(currentUser)
                            .orElseThrow { ProfileNotFoundException(s = currentUser) }

                    UserRating(
                            id = UserRatingId(profileId = profile.profileId, ratingId = ratingId),
                            ratingValue = 0 // Начальное значение голоса пользователя
                    )
                }

        val rating = ratingRepository.findById(ratingId)
                .orElseThrow { RatingNotFoundException(ratingId = ratingId) }

        val previousRatingValue = userRating.ratingValue

        // Если пользователь уже голосовал отрицательно, возвращаем значения обратно
        if (previousRatingValue < 0) {
            userRating.ratingValue = 0
            rating.ratingValue += 1
            ratingRepository.save(rating)
            userRatingRepository.save(userRating)
            return
        }

        // Если предыдущий голос был положительным, то значение голоса уменьшается на 2, иначе на 1
        userRating.ratingValue = -1

        // Уменьшаем рейтинг на 1 или 2 в зависимости от предыдущего голоса пользователя
        rating.ratingValue -= if (previousRatingValue > 0) 2 else 1

        // Сохраняем или обновляем записи в репозиториях
        ratingRepository.save(rating)
        userRatingRepository.save(userRating)
    }


}