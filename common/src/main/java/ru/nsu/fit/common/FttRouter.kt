package ru.nsu.fit.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FttRouter {

    private val _currentScreen = MutableStateFlow<FttScreens>(FttScreens.AuthScreen)
    val currentScreen: StateFlow<FttScreens>
        get() = _currentScreen

    fun openAuthScreen() {
        _currentScreen.value = FttScreens.AuthScreen
    }

    fun openScheduleScreen() {
        _currentScreen.value = FttScreens.ScheduleScreen
    }

    fun openRegisterScreen() {
        _currentScreen.value = FttScreens.RegisterScreen
    }
}
