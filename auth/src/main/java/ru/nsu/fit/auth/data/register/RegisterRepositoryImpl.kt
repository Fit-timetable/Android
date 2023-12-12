package ru.nsu.fit.auth.data.register

import ru.nsu.fit.auth.domain.RegisterRepository
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(
    private val service: RegisterService
) : RegisterRepository {
    override suspend fun requestSignup(mail: String) {
        service.requestSignup(MailRemote(mail))
    }

    override suspend fun confirmSignup(mail: String, password: String, confirmCode: Int) {
        service.confirmSignup(registerRemoteModel {
            email = mail
            this.password = password
            this.confirmationCode = confirmCode
        })
    }
}