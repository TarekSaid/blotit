package org.blotit.rating.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DataSheetTest : StringSpec({

//    data class Calculation(val req: RateRequest, val expectedTotal: DataSheet)

    "creating a new data sheet should calculate the total" {
        val req = DataSheet(
            mono = Inkblot(
                type = Type.MONO,
                location = Location(G = 3, primaryP = 7, secondaryP = 2),
                determinants = Determinants(FPlus = 7.0, FMinus = 1.0, FZero = 1.0, mPrimary = 1.0, lPrimary = 1.0, cLine = 1.0),
                content = Content(A = 4, pA = 2, H = 4, ml = 1, rl = 1),
                popularity = 3,
                summary = Summary(Elab = 10.0, tri = 32, T = 258, R = 12)
            ),
            color = Inkblot(
                type = Type.COLOR,
                location = Location(primaryP = 16, secondaryP = 2, E = 2),
                determinants = Determinants(FPlus = 9.0, FMinus = 3.0, FZero = 1.0, mPrimary = 1.0, mSecondary = 1.0, lLine = 0.5, cLine = 2.5, FC = 1.5, CF = 2.0, C = 1.0),
                content = Content(A = 10, H = 2, pH = 2, al = 1, an = 1, art = 1, bt = 1, obj = 1, vst = 1),
                popularity = 1,
                summary = Summary(Elab = 3.0, zSquared = 6.0, tri = 33, T = 474, R = 20)
            )
        )

        val expectedTotal = Inkblot(
            type = Type.TOTAL,
            location = Location(G = 3, primaryP = 23, secondaryP = 4, E = 2),
            determinants = Determinants(FPlus = 16.0, FMinus = 4.0, FZero = 2.0, mPrimary = 2.0, mSecondary = 1.0, lPrimary = 1.0, lLine = 0.5, cLine = 3.5, FC = 1.5, CF = 2.0, C = 1.0),
            content = Content(A = 14, pA = 2, H = 6, pH = 2, al = 1, an = 1, art = 1, bt = 1, ml = 1, obj = 1, rl = 1, vst = 1),
            popularity = 4,
            summary = Summary(Elab = 13.0, zSquared = 6.0, tri = 65, T = 732, R = 32)
        )

        req.total shouldBe expectedTotal
    }
})
