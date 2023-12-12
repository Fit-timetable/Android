package ru.nsu.fit.auth.presentation.auth.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import ru.nsu.fit.auth.presentation.auth.AuthState
import ru.nsu.fit.auth.presentation.theme.ProgressBar
import ru.nsu.fit.auth.presentation.theme.ProgrssBapBackground
import ru.nsu.fit.auth.presentation.theme.ScreenBackGround

@Composable
fun AuthScreen(
    state: AuthState,
    onClickAuth: (mail: String, password: String) -> Unit,
    onClickRegister: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(ScreenBackGround)
            .fillMaxHeight()
            .padding(start = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        if (state.loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth(1F)
                    .height(5.dp),
                color = ProgressBar,
                trackColor = ProgrssBapBackground
            )
        }
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            Column {
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text("Email") }
                )
            }

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                label = { Text("Password") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    modifier = Modifier.padding(top = 5.dp),
                    onClick = { onClickAuth(email, password) }) {
                    Text(text = "Login")
                }
                OutlinedButton(
                    modifier = Modifier.padding(top = 5.dp),
                    onClick = { onClickRegister() }) {
                    Text(text = "Register")
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewAuthScreen() {
    val state = AuthState()
    AuthScreen(state, { _, _ -> }, { })
}