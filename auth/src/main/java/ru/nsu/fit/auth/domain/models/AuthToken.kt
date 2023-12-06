package ru.nsu.fit.auth.domain.models

data class AuthToken(
    var authToken: String,
    var authTokenExpire: String,
)
