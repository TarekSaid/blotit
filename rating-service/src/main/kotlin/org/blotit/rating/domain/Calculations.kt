package org.blotit.rating.domain

import java.math.BigDecimal

enum class Classification {
    LOW, MEAN, HIGH
}

data class Index(val value: Double, val classification: Classification)

class Calculations(inkblot: Inkblot) {
    val tOverR = (inkblot.location.G + inkblot.content.A).toBigDecimal() / BigDecimal("2.0")
}