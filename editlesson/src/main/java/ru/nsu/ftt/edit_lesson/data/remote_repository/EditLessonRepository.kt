package ru.nsu.ftt.edit_lesson.data.remote_repository

import ru.nsu.ftt.edit_lesson.presentation.view.LessonForm
import javax.inject.Inject

class EditLessonRepository @Inject constructor(
    private val editLessonService: EditLessonService
) {

    suspend fun createLesson(lessonForm: LessonForm) {
        editLessonService.createLesson(lessonForm)
    }
}