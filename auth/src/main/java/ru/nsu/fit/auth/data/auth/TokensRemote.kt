package ru.nsu.fit.auth.data.auth

import kotlinx.serialization.Serializable
import ru.nsu.fit.common.Tokens

@Serializable
data class TokensRemote(
    var authToken: String,
    var authTokenExpire: String,
    var refreshToken: String,
    var refreshTokenExpire: String,
) {
    fun mapToTokens() : Tokens {
        return Tokens(
            authToken, authTokenExpire, refreshToken, refreshTokenExpire
        )
    }
}