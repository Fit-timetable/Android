package ru.nsu.ftt.edit_lesson.presentation.view

data class EditLessonState(
    val error: InputError = InputError.NO,
    val isLoading: Boolean = false
)

enum class InputError {
    NO,
    LESSON,
    TYPE,
    PARITY,
    DAY_OF_WEEK,
    TIME_START,
    ROOM,
    LINK
}