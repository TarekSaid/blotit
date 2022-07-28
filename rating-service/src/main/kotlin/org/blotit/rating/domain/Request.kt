package org.blotit.rating.domain

import java.math.BigDecimal
import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

data class DataSheet(val mono: Inkblot, val color: Inkblot) {
    val total: Inkblot = mono + color
}

data class Inkblot(
    val location: Location,
    val determinants: Determinants,
    val content: Content,
    val popularity: Int,
    val summary: Summary
) {
    operator fun plus(other: Inkblot): Inkblot {
        return Inkblot(
            location = this.location + other.location,
            determinants = this.determinants + other.determinants,
            content = this.content + other.content,
            popularity = this.popularity + other.popularity,
            summary = this.summary + other.summary
        )
    }
}

interface Category

inline operator fun <reified T : Category> Category.plus(other: T): T {
    val constructor = T::class.primaryConstructor ?: throw NullPointerException()
    val members = T::class.declaredMemberProperties

    val sums = constructor.parameters.map { param ->
        val prop = members.first { member -> member.name == param.name }

        when (prop.returnType) {
            Int::class.createType() -> prop.get(this as T) as Int + prop.get(other) as Int
            else -> prop.get(this as T) as BigDecimal + prop.get(other) as BigDecimal
        }
    }

    return constructor.call(*sums.toTypedArray())
}

data class Location(
    val G: Int = 0,
    val primaryP: Int = 0,
    val secondaryP: Int = 0,
    val E: Int = 0,
    val GE: Int = 0,
    val pLine: Int = 0,
    val PG: Int = 0,
    val GP: Int = 0
) : Category

data class Determinants(
    val FPlus: BigDecimal = BigDecimal.ZERO,
    val FMinus: BigDecimal = BigDecimal.ZERO,
    val FZero: BigDecimal = BigDecimal.ZERO,
    val mPrimary: BigDecimal = BigDecimal.ZERO,
    val mSecondary: BigDecimal = BigDecimal.ZERO,
    val mLine: BigDecimal = BigDecimal.ZERO,
    val psPrimary: BigDecimal = BigDecimal.ZERO,
    val psSecondary: BigDecimal = BigDecimal.ZERO,
    val psLine: BigDecimal = BigDecimal.ZERO,
    val lPrimary: BigDecimal = BigDecimal.ZERO,
    val lSecondary: BigDecimal = BigDecimal.ZERO,
    val lLine: BigDecimal = BigDecimal.ZERO,
    val cLine: BigDecimal = BigDecimal.ZERO,
    val FC: BigDecimal = BigDecimal.ZERO,
    val CF: BigDecimal = BigDecimal.ZERO,
    val C: BigDecimal = BigDecimal.ZERO
) : Category

data class Content(
    val A: Int = 0,
    val pA: Int = 0,
    val H: Int = 0,
    val pH: Int = 0,
    val ab: Int = 0,
    val al: Int = 0,
    val an: Int = 0,
    val ant: Int = 0,
    val arq: Int = 0,
    val art: Int = 0,
    val bt: Int = 0,
    val ci: Int = 0,
    val fg: Int = 0,
    val ggr: Int = 0,
    val ml: Int = 0,
    val mp: Int = 0,
    val nat: Int = 0,
    val nv: Int = 0,
    val obj: Int = 0,
    val pz: Int = 0,
    val rl: Int = 0,
    val sg: Int = 0,
    val sx: Int = 0,
    val vst: Int = 0
) : Category

data class Summary(
    val Elab: BigDecimal = BigDecimal.ZERO,
    val zSquared: BigDecimal = BigDecimal.ZERO,
    val tri: Int = 0,
    val T: Int = 0,
    val R: Int = 0
) : Category