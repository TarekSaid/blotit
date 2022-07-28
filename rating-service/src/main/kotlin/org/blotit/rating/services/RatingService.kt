package org.blotit.rating.services

import org.blotit.rating.domain.Calculations
import org.blotit.rating.domain.Inkblot
import org.blotit.rating.domain.Rating
import org.springframework.stereotype.Service

@Service
class RatingService {
    suspend fun rate(inkblot: Inkblot): Rating {
        val calc = Calculations(inkblot)
        return Rating(gOverM = calc.tOverR)
    }
}