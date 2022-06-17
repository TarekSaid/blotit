package org.blotit.rating

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RatingApp

fun main(args: Array<String>) {
    runApplication<RatingApp>(*args)
}