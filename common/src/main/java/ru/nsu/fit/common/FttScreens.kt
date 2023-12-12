package ru.nsu.fit.common

sealed class FttScreens {
    data object AuthScreen : FttScreens()
    data object RegisterScreen : FttScreens()
    data object ScheduleScreen : FttScreens()
}
