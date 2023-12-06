package ru.nsu.fit.auth.data.auth

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @GET("/jwt/tokens")
    suspend fun auth(@Header("Authorization") authBase64: ByteArray): TokensRemote

    @POST("/jwt/refresh")
    suspend fun authRefresh(@Header("Authorization") refreshToken: String): AuthTokensRemote

    @POST("/jwt/logout")
    suspend fun logout(@Header("Authorization") refreshToken: String)
}