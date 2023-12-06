package ru.nsu.fit.auth.data.register

import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {
    @POST("/request-signup")
    suspend fun requestSignup(@Body mail: MailRemote)

    @POST("/confirm-signup")
    suspend fun confirmSignup(@Body registerRemoteModel: RegisterRemoteModel)
}