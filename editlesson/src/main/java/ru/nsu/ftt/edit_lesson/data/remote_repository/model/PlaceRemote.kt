package ru.nsu.ftt.edit_lesson.data.remote_repository.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceRemote(
    @SerialName("meetLink")
    var meetLink: String? = "",
    @SerialName("room")
    var room: String = "",
)

fun placeRemote(block: PlaceRemote.() -> Unit) = PlaceRemote().apply(block)