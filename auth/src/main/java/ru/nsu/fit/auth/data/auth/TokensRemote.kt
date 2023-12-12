package ru.nsu.fit.auth.data.auth

import kotlinx.serialization.Serializable
import ru.nsu.fit.common.Tokens

@Serializable
data class TokensRemote(
    var accessToken: String,
    var accessTokenExpiry: String,
    var refreshToken: String,
    var refreshTokenExpiry: String,
) {
    fun mapToTokens(): Tokens {
        return Tokens(
            accessToken = accessToken,
            accessTokenExpiry = accessTokenExpiry,
            refreshToken = refreshToken,
            refreshTokenExpiry = refreshTokenExpiry
        )
    }
}