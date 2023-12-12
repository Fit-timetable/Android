package ru.nsu.fit.auth.data.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRemoteModel(
    var email: String,
    var password: String,
    var confirmationCode: Int
)

fun registerRemoteModel(block: RegisterRemoteModel.() -> Unit) =
    RegisterRemoteModel("", "", 0).apply(block)