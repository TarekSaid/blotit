package org.blotit.rating.services

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.mockk
import org.blotit.rating.domain.DataSheet
import org.blotit.rating.domain.Inkblot

class RatingServiceTest : StringSpec({
    val service = AdultRatingService()
    val inkblot: Inkblot = mockk()


})
