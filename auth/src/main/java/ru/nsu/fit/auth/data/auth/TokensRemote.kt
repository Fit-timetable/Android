package ru.nsu.fit.auth.data.auth

import kotlinx.serialization.Serializable
import ru.nsu.fit.auth.data.KSerializerDate
import ru.nsu.fit.common.Tokens
import java.util.Date

@Serializable
data class TokensRemote(
    var accessToken: String,
    @Serializable(with = KSerializerDate::class)
    var accessTokenExpiry: Date,
    var refreshToken: String,
    @Serializable(with = KSerializerDate::class)
    var refreshTokenExpiry: Date,
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