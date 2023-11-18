package ru.nsu.fit.timetable.domain.models


data class Place(
    var remoteLink: String = "",
    var room: String = "",
)

fun place(block: Place.() -> Unit) = Place().apply(block)