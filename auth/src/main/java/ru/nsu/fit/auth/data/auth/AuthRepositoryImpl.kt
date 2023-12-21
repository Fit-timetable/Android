package ru.nsu.fit.auth.data.auth

import android.util.Base64
import ru.nsu.fit.auth.domain.AuthRepository
import ru.nsu.fit.auth.domain.models.AuthToken
import ru.nsu.fit.common.Tokens
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: AuthService
) : AuthRepository {
    override suspend fun auth(mail: String, password: String): Tokens {
        return service.auth(
            authBase64 = "Basic " + Base64.encodeToString("$mail:$password".toByteArray(), Base64.NO_WRAP)
        ).mapToTokens()

    }

    override suspend fun authRefresh(refreshToken: String): AuthToken {
        return service.authRefresh(refreshToken).mapToAuthTokens()
    }

    override suspend fun logout(refreshToken: String) {
        service.logout(refreshToken)
    }
}