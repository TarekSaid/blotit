package org.blotit.rating.domain

import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

data class DataSheet(val mono: Inkblot, val color: Inkblot) {
    val total: Inkblot = mono + color
}

enum class Type {
    MONO, COLOR, TOTAL
}

data class Inkblot(
    val type: Type,
    val location: Location,
    val determinants: Determinants,
    val content: Content,
    val popularity: Int,
    val summary: Summary
) {
    operator fun plus(other: Inkblot): Inkblot {
        return Inkblot(
            type = Type.TOTAL,
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
            else -> prop.get(this as T) as Double + prop.get(other) as Double
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
    val FPlus: Double = 0.0,
    val FMinus: Double = 0.0,
    val FZero: Double = 0.0,
    val mPrimary: Double = 0.0,
    val mSecondary: Double = 0.0,
    val mLine: Double = 0.0,
    val psPrimary: Double = 0.0,
    val psSecondary: Double = 0.0,
    val psLine: Double = 0.0,
    val lPrimary: Double = 0.0,
    val lSecondary: Double = 0.0,
    val lLine: Double = 0.0,
    val cLine: Double = 0.0,
    val FC: Double = 0.0,
    val CF: Double = 0.0,
    val C: Double = 0.0
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
    val Elab: Double = 0.0,
    val zSquared: Double = 0.0,
    val tri: Int = 0,
    val T: Int = 0,
    val R: Int = 0
) : Category