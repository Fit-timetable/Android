package ru.nsu.fit.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LessonType {
    @SerialName("LECTURE")
    LECTURE,

    @SerialName("SEMINAR")
    SEMINAR,

    @SerialName("OPENING")
    WINDOW,

    @SerialName("PRACTICE")
    PRACTICE;

    fun mapToRus(): String {
        return when (this) {
            LECTURE -> "Лекция"
            SEMINAR -> "Семинар"
            PRACTICE -> "Практика"
            WINDOW -> "Окно"
        }
    }
}

fun String.mapToLessonType(): LessonType {
    return when (this) {
        "Лекция" -> LessonType.LECTURE
        "Семинар" -> LessonType.SEMINAR
        "Практика" -> LessonType.PRACTICE
        "Окно" -> LessonType.WINDOW
        else -> LessonType.WINDOW
    }
}