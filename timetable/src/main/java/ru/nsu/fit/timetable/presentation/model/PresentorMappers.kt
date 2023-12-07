package ru.nsu.fit.timetable.presentation.model

import ru.nsu.fit.timetable.domain.models.Lesson


fun Lesson.mapToLessonUi(numberLesson : Int): LessonUi {
    return lessonUi {
        this.id = this@mapToLessonUi.id
        this.startTime = this@mapToLessonUi.startTime
        this.indexInDay = numberLesson
        this.finishTime = this@mapToLessonUi.finishTime
        this.subject = this@mapToLessonUi.subject
        this.typeLesson = this@mapToLessonUi.lessonType.mapToLessonTypeUi()
        this.room = this@mapToLessonUi.place.room
        this.teacher = this@mapToLessonUi.teacher
        this.parity = this@mapToLessonUi.parity
    }
}