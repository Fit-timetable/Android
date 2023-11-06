package ru.nsu.fit.timetable.presentation

import ru.nsu.fit.timetable.presentation.model.ChoiceGroupUi
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi

data class TimeTableState(
    val lessonsUi: List<LessonUi>,
    val group: ChoiceGroupUi,
    val dates: List<DateUi>
)