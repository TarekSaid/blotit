package org.blotit.rating.domain

import java.math.BigDecimal

data class Response(val mono: Rating, val color: Rating, val total: Rating)

data class Rating(var gOverM: BigDecimal = BigDecimal.ZERO)