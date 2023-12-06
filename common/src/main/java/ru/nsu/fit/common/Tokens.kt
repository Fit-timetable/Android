package ru.nsu.fit.common

import java.sql.Time
import java.util.Calendar

data class Tokens(
    var authToken: String = "",
    var authTokenExpire: String = "",
    var refreshToken: String = "",
    var refreshTokenExpire: String = "",
) {
    fun isRefreshTokenAvailable() : Boolean {
        return Time.valueOf(refreshTokenExpire).after(Calendar.getInstance().time)
    }

    fun isAuthTokenAvailable() : Boolean {
        return Time.valueOf(authTokenExpire).after(Calendar.getInstance().time)
    }
}
