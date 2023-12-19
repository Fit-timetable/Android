package ru.nsu.fit.auth.presentation.auth.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import ru.nsu.fit.auth.presentation.auth.AuthState
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
            .fillMaxHeight(),
        contentAlignment = Alignment.Center,
    ) {
        Column(modifier = Modifier.width(IntrinsicSize.Max)) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                label = { Text("Email") },
            )

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
                ButtonWithLoader(modifier = Modifier.padding(top = 5.dp), state, "Login") {
                    onClickAuth(email, password)
                }
                Button(
                    modifier = Modifier.padding(top = 5.dp),
                    onClick = { onClickRegister() }) {
                    Text(text = "Register")
                }
            }
        }
    }
}

@Composable
fun ButtonWithLoader(
    modifier: Modifier = Modifier,
    state: AuthState,
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
fun PreviewAuthScreen() {
    val state = AuthState()
    AuthScreen(state, { _, _ -> }, { })
}