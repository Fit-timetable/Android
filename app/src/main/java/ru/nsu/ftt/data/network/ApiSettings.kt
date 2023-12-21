package ru.nsu.ftt.data.network

data class ApiSettings(
    val baseUrl: String = "http://192.168.1.39:8080",
    val userName: String = "",
    var authToken: String = ""
) {
    companion object {
        public const val TIME_OUT_DELAY = 15L
    }
}