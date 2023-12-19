package ru.nsu.fit.auth.presentation.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.domain.AuthInteractor
import ru.nsu.fit.common.FttRouter
import ru.nsu.fit.common.runCatchingNonCancellation
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val router: FttRouter
) : ViewModel() {

    private var _stateFlow: MutableStateFlow<AuthState> =
        MutableStateFlow(AuthState())
    var stateFlow: StateFlow<AuthState> = _stateFlow
    private val state = _stateFlow.value

    fun auth(email: String, password: String) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                Log.d("FTT_TAG", "auth init")
                _stateFlow.emit(state.copy(loading = true, error = null))
                interactor.auth(email, password)
                router.openScheduleScreen()
                Log.d("FTT_TAG", "auth success")
            }.getOrElse {
                Log.d("FTT_TAG", "auth failed ${it.stackTraceToString()}")
                router.sendToast("Ошибка авторизации")
                _stateFlow.emit(state.copy(loading = false, error = it.message))
            }
        }
    }

    fun onRegister() {
        viewModelScope.launch {
            router.openRegisterScreen()
        }
    }
}