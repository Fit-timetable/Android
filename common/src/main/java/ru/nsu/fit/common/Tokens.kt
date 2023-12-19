package ru.nsu.fit.common

import java.util.Calendar
import java.util.Date

data class Tokens(
    var accessToken: String = "",
    var accessTokenExpiry: Date = Date(),
    var refreshToken: String = "",
    var refreshTokenExpiry: Date = Date(),
) {
    fun isRefreshTokenAvailable(): Boolean {
        return refreshTokenExpiry.after(Calendar.getInstance().time)
    }

    fun isAuthTokenAvailable(): Boolean {
        return accessTokenExpiry.after(Calendar.getInstance().time)
    }
}
