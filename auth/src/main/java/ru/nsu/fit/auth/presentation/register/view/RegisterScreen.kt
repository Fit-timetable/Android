package ru.nsu.fit.auth.presentation.register.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nsu.fit.auth.presentation.theme.ScreenBackGround

@Composable
fun RegisterScreen(onClickConfirm: (mail: String) -> Unit) {
    Box(
        modifier = Modifier
            .background(ScreenBackGround)
            .fillMaxHeight()
            .padding(start = 10.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            var text by remember { mutableStateOf("") }

            OutlinedTextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Email") }
            )

            OutlinedButton(
                modifier = Modifier.padding(top = 5.dp),
                onClick = { onClickConfirm(text) }) {
                Text(text = "Отправить")
            }
        }
    }
}

@Composable
@Preview
fun PreviewRegisterScreen() {
    RegisterScreen({ _ -> })
}