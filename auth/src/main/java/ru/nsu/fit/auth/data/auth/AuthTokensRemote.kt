package ru.nsu.fit.auth.data.auth

import kotlinx.serialization.Serializable
import ru.nsu.fit.auth.domain.models.AuthToken

@Serializable
data class AuthTokensRemote(
    var authToken: String,
    var authTokenExpire: String,
) {
    fun mapToAuthTokens(): AuthToken {
        return AuthToken(authToken, authTokenExpire)
    }
}
