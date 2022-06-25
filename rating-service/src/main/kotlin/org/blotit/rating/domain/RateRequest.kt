package org.blotit.rating.domain

import kotlin.reflect.full.createType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.primaryConstructor

data class RateRequest(val mono: AnswerSheet, val color: AnswerSheet) {
    val total: AnswerSheet = mono + color
}

enum class Type {
    MONO, COLOR, TOTAL
}

data class AnswerSheet(
    val type: Type,
    val modalities: Modalities,
    val determinants: Determinants,
    val contents: Contents,
    val V: Int,
    val answers: Answers
) {
    operator fun plus(other: AnswerSheet): AnswerSheet {
        return copy(
            type = Type.TOTAL,
            modalities = this.modalities + other.modalities,
            determinants = this.determinants + other.determinants,
            contents = this.contents + other.contents,
            V = this.V + other.V,
            answers = this.answers + other.answers
        )
    }
}

interface Summable

inline operator fun <reified T : Summable> Summable.plus(other: T): T {
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

data class Modalities(
    val G: Int = 0,
    val primaryP: Int = 0,
    val secondaryP: Int = 0,
    val E: Int = 0,
    val GE: Int = 0,
    val pLine: Int = 0,
    val PG: Int = 0,
    val GP: Int = 0
) {
    operator fun plus(other: Modalities): Modalities {
        val constructor = Modalities::class.primaryConstructor ?: throw NullPointerException()
        val members = Modalities::class.declaredMemberProperties

        val sums = constructor.parameters.map { param ->
            val prop = members.first { member -> member.name == param.name }
            prop.get(this) as Int + prop.get(other) as Int
        }

        return constructor.call(*sums.toTypedArray())
    }
}

data class Determinants(
    val FPlus: Int = 0,
    val FMinus: Int = 0,
    val FZero: Int = 0,
    val mPrimary: Int = 0,
    val mSecondary: Int = 0,
    val mLine: Int = 0,
    val psPrimary: Int = 0,
    val psSecondary: Int = 0,
    val psLine: Int = 0,
    val lPrimary: Int = 0,
    val lSecondary: Int = 0,
    val lLine: Int = 0,
    val cLine: Int = 0,
    val FC: Int = 0,
    val CF: Int = 0,
    val C: Int = 0
) : Summable

data class Contents(
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
) : Summable

data class Answers(
    val Elab: Double = 0.0,
    val zSquared: Double = 0.0,
    val tri: Int = 0,
    val T: Int = 0,
    val R: Int = 0
) : Summable