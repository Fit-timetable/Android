package ru.nsu.fit.auth.domain

interface RegisterRepository {
    suspend fun requestSignup(mail: String)

    suspend fun confirmSignup(mail: String, password: String, confirmCode: Int)
}