package ru.nsu.fit.auth.domain

import ru.nsu.fit.common.ApiSettings
import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val registerRepository: RegisterRepository,
    private val apiSettings: ApiSettings,
) {
    suspend fun auth(mail: String, password: String) {
        val result = authRepository.auth(mail, password)
        apiSettings.tokens = result
    }

    suspend fun authRefresh() {
        val result = authRepository.authRefresh(apiSettings.tokens.refreshToken)
        apiSettings.tokens.accessToken = result.authToken
        apiSettings.tokens.accessTokenExpiry = result.authTokenExpire
    }

    suspend fun logout() {
        if (apiSettings.tokens.isRefreshTokenAvailable())
            authRepository.logout(apiSettings.tokens.refreshToken)
    }

    suspend fun requestSignup(mail: String) {
        apiSettings.userName = mail
        registerRepository.requestSignup(mail)
    }

    suspend fun confirmSignup(mail: String, password: String, confirmCode: Int) {
        registerRepository.confirmSignup(mail, password, confirmCode)
    }

}