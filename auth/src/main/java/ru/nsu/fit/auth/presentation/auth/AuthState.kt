package ru.nsu.fit.auth.presentation.auth

data class AuthState(
    val loading: Boolean = false,
    val error: String? = null
)