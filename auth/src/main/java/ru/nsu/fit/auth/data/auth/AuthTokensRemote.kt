package ru.nsu.fit.auth.data.auth

import kotlinx.serialization.Serializable
import ru.nsu.fit.auth.data.KSerializerDate
import ru.nsu.fit.auth.domain.models.AuthToken
import java.util.Date

@Serializable
data class AuthTokensRemote(
    var authToken: String,
    @Serializable(with = KSerializerDate::class)
    var authTokenExpire: Date,
) {
    fun mapToAuthTokens(): AuthToken {
        return AuthToken(authToken, authTokenExpire)
    }
}
