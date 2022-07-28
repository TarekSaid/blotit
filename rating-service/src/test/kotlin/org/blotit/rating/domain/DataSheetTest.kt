package org.blotit.rating.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.TEN

class DataSheetTest : StringSpec({

    // TODO create more DataSheets for data driven tests
    // data class Calculation(val req: RateRequest, val expectedTotal: DataSheet)

    "creating a new data sheet should calculate the total" {
        val req = DataSheet(
            mono = Inkblot(
                location = Location(G = 3, primaryP = 7, secondaryP = 2),
                determinants = Determinants(FPlus = BigDecimal("7"), FMinus = ONE, FZero = ONE, mPrimary = ONE, lPrimary = ONE, cLine = ONE),
                content = Content(A = 4, pA = 2, H = 4, ml = 1, rl = 1),
                popularity = 3,
                summary = Summary(Elab = TEN, tri = 32, T = 258, R = 12)
            ),
            color = Inkblot(
                location = Location(primaryP = 16, secondaryP = 2, E = 2),
                determinants = Determinants(FPlus = BigDecimal("9"), FMinus = BigDecimal("3"), FZero = ONE, mPrimary = ONE, mSecondary = ONE, lLine = BigDecimal("0.5"),
                    cLine = BigDecimal("2.5"), FC = BigDecimal("1.5"), CF = BigDecimal("2"), C = ONE),
                content = Content(A = 10, H = 2, pH = 2, al = 1, an = 1, art = 1, bt = 1, obj = 1, vst = 1),
                popularity = 1,
                summary = Summary(Elab = BigDecimal("3"), zSquared = BigDecimal("6"), tri = 33, T = 474, R = 20)
            )
        )

        val expectedTotal = Inkblot(
            location = Location(G = 3, primaryP = 23, secondaryP = 4, E = 2),
            determinants = Determinants(FPlus = BigDecimal("16"), FMinus = BigDecimal("4"), FZero = BigDecimal("2"), mPrimary = BigDecimal("2"), mSecondary = ONE,
                lPrimary = ONE, lLine = BigDecimal("0.5"), cLine = BigDecimal("3.5"), FC = BigDecimal("1.5"), CF = BigDecimal("2"), C = ONE),
            content = Content(A = 14, pA = 2, H = 6, pH = 2, al = 1, an = 1, art = 1, bt = 1, ml = 1, obj = 1, rl = 1, vst = 1),
            popularity = 4,
            summary = Summary(Elab = BigDecimal("13"), zSquared = BigDecimal("6"), tri = 65, T = 732, R = 32)
        )

        req.total shouldBe expectedTotal
    }
})
