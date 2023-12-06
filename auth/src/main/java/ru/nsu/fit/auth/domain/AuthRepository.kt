package ru.nsu.fit.auth.domain

import ru.nsu.fit.auth.domain.models.AuthToken
import ru.nsu.fit.common.Tokens

interface AuthRepository {
    suspend fun auth(mail: String, password: String): Tokens
    suspend fun authRefresh(refreshToken: String): AuthToken
    suspend fun logout(refreshToken: String)
}