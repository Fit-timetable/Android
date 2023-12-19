package ru.nsu.fit.auth.presentation.register.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nsu.fit.auth.presentation.register.RegisterState
import ru.nsu.fit.auth.presentation.theme.FTTTheme
import ru.nsu.fit.auth.presentation.theme.ScreenBackGround

@Composable
fun RegisterScreen(
    modifier: Modifier,
    state: RegisterState,
    onClickConfirm: (mail: String) -> Unit,
    onClickRegister: (mail: String, pass: String, code: String) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier
            .background(ScreenBackGround)
            .fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmCode by remember { mutableStateOf("") }
            var visible by remember {
                mutableStateOf(state.requestLoginSuccess)
            }
            visible = state.requestLoginSuccess
            AnimatedVisibility(!visible) {
                Column {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        label = { Text("Email") }
                    )

                    ButtonWithLoader(modifier, state, "Отправить") {
                        onClickConfirm(email)
                    }
                }
            }
            AnimatedVisibility(visible) {
                Column {
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        label = { Text("Password") }
                    )

                    OutlinedTextField(
                        value = confirmCode,
                        onValueChange = {
                            confirmCode = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text("Confirm code") }
                    )

                    ButtonWithLoader(modifier.padding(top = 5.dp), state, "Отправить") {
                        onClickRegister(email, password, confirmCode)
                    }
                }
            }
        }
    }
}

@Composable
fun ButtonWithLoader(
    modifier: Modifier = Modifier,
    state: RegisterState,
    buttonText: String,
    onClick: () -> Unit
) {
    var progressIndicatorVisible by remember { mutableStateOf(false) }
    progressIndicatorVisible = state.loading
    Button(
        onClick = { onClick() },
        modifier = modifier.animateContentSize()
    ) {
        if (progressIndicatorVisible) {
            CircularProgressIndicator(
                color = ScreenBackGround,
                strokeWidth = 2.dp,
                modifier = Modifier.size(15.dp)
            )
        }
        Text(
            buttonText,
            modifier = Modifier.padding(start = if (progressIndicatorVisible) 8.dp else 0.dp)
        )
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    val state = RegisterState()
    FTTTheme {
        RegisterScreen(Modifier, state, { _ -> }, { _, _, _ -> })
    }
}