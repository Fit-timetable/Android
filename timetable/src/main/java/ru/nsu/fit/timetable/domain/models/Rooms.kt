package ru.nsu.fit.timetable.domain.models

data class Rooms(
    val rooms: List<String> = emptyList()
)

fun rooms(block: Rooms.() -> Unit) = Rooms().apply(block)