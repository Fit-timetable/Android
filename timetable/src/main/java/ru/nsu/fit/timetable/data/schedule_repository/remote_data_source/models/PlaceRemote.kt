package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRemote(
    @SerialName("meetLink")
    val meetLink: String? = "",
    @SerialName("room")
    val room: String = "",
)

fun placeRemote(block: PlaceRemote.() -> Unit) = PlaceRemote().apply(block)