package ru.nsu.fit.timetable.presentation

import ru.nsu.fit.timetable.presentation.model.ChoiceGroupUi
import ru.nsu.fit.timetable.presentation.model.ClassUi

data class TimeTableState(
    val classUi: ClassUi,
    val group: ChoiceGroupUi
)