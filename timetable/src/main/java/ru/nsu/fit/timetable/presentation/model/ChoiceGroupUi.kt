package ru.nsu.fit.timetable.presentation.model

data class ChoiceGroupUi(
    val id : Int,
    val group : String,
    val date : String,
    val month: String,
    val parityWeek : String,
    val fixedSchedule: Boolean
)
