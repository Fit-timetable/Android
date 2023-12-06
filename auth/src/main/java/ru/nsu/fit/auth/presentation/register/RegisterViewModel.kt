package ru.nsu.fit.auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.domain.AuthInteractor
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val interactor: AuthInteractor
) : ViewModel() {

    fun sendRequest(mail: String) {
        viewModelScope.launch {
            interactor.requestSignup(mail)
        }
    }
}