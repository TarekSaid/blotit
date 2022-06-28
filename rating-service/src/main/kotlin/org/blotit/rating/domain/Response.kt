package org.blotit.rating.domain

data class Response(val mono: Rating, val color: Rating, val total: Rating)

data class Rating(var gOverM: Int = 0)