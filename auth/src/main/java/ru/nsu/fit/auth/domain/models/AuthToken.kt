package ru.nsu.fit.auth.domain.models

import java.util.Date

data class AuthToken(
    var authToken: String,
    var authTokenExpire: Date,
)
