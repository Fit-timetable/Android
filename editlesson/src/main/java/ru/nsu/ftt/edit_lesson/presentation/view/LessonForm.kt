package ru.nsu.ftt.edit_lesson.presentation.view

import ru.nsu.fit.common.models.LessonParity
import ru.nsu.fit.common.models.LessonType
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.LessonDateRemote
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.PlaceRemote
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.lessonDateRemote
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.placeRemote

data class LessonForm(
    var subjectName: String = "",
    var type: LessonType = LessonType.SEMINAR,
    var date: LessonDateRemote = lessonDateRemote {},
    var place: PlaceRemote = placeRemote { },
    var parity: LessonParity = LessonParity.ALWAYS
)