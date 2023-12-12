package ru.nsu.ftt.utils

import okhttp3.Interceptor
import okhttp3.Response
import ru.nsu.fit.common.ApiSettings
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val apiSettings: ApiSettings
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val builder = originalRequest
            .newBuilder()
            .header("Authorization", "Bearer " + apiSettings.tokens.accessToken)
        return chain.proceed(builder.build())
    }
}
