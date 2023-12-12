package ru.nsu.fit.auth.data.register

import kotlinx.serialization.Serializable

@Serializable
data class MailRemote(
    var email: String
)
