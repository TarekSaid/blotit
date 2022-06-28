package org.blotit.rating.services

import org.blotit.rating.domain.Inkblot
import org.blotit.rating.domain.Rating
import org.springframework.stereotype.Service

interface RatingService {
    suspend fun rate(inkblot: Inkblot): Rating
}

@Service
class AdultRatingService : RatingService {
    override suspend fun rate(inkblot: Inkblot): Rating {
        return Rating()
    }
}