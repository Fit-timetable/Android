package ru.nsu.fit.auth.presentation.register.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
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
import ru.nsu.fit.auth.presentation.theme.ProgressBar
import ru.nsu.fit.auth.presentation.theme.ProgrssBapBackground
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
        if (state.loading) {
            LinearProgressIndicator(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(1F)
                    .height(5.dp),
                color = ProgressBar,
                trackColor = ProgrssBapBackground
            )
        }
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

                    OutlinedButton(
                        modifier = Modifier.padding(top = 5.dp),
                        onClick = { onClickConfirm(email) }) {
                        Text(text = "Отправить")
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

                    OutlinedButton(
                        modifier = Modifier.padding(top = 5.dp),
                        onClick = { onClickRegister(email, password, confirmCode) }) {
                        Text(text = "Отправить")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    val state = RegisterState()
    RegisterScreen(Modifier, state, { _ -> }, { _, _, _ -> })
}