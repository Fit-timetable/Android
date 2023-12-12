package ru.nsu.fit.common

import java.sql.Time
import java.util.Calendar

data class Tokens(
    var accessToken: String = "",
    var accessTokenExpiry: String = "",
    var refreshToken: String = "",
    var refreshTokenExpiry: String = "",
) {
    fun isRefreshTokenAvailable() : Boolean {
        return Time.valueOf(refreshTokenExpiry).after(Calendar.getInstance().time)
    }

    fun isAuthTokenAvailable() : Boolean {
        return Time.valueOf(accessTokenExpiry).after(Calendar.getInstance().time)
    }
}
