package ru.nsu.ftt.edit_lesson.data.remote_repository

import retrofit2.http.Body
import retrofit2.http.POST
import ru.nsu.ftt.edit_lesson.presentation.view.LessonForm

interface EditLessonService {


    @POST("/lesson")
    suspend fun createLesson(@Body lessonForm: LessonForm)
}