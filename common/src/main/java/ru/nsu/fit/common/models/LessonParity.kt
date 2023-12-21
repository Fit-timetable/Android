package ru.nsu.fit.common.models

import kotlinx.serialization.SerialName

enum class LessonParity {
    @SerialName("EVEN")
    EVEN,

    @SerialName("ODD")
    ODD,

    @SerialName("ALWAYS")
    ALWAYS;

    fun mapToRus(): String {
        return when (this) {
            EVEN -> "Четная"
            ODD -> "Нечетная"
            ALWAYS -> "Каждая неделя"
        }
    }
}

fun String.mapToLessonParity(): LessonParity {
    return when (this) {
        "Четная" -> LessonParity.EVEN
        "Нечетная" -> LessonParity.ODD
        "Каждая неделя" -> LessonParity.ALWAYS
        else -> LessonParity.ALWAYS
    }
}