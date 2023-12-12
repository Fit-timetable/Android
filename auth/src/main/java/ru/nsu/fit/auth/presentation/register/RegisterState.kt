package ru.nsu.fit.auth.presentation.register

data class RegisterState(
    val loading: Boolean = false,
    val error: String? = null,
    val requestLoginSuccess: Boolean = false
)