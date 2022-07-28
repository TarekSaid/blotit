package org.blotit.rating.services

import io.kotest.core.spec.style.StringSpec
import io.mockk.mockk
import org.blotit.rating.domain.Inkblot

class RatingServiceTest : StringSpec({
    val service = RatingService()
    val inkblot: Inkblot = mockk()
})
