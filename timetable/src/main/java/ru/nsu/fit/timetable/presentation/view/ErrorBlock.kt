package ru.nsu.fit.timetable.presentation.view

import android.view.Gravity
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ErrorBlock(error: String) {
    val context = LocalContext.current
    val toast = Toast.makeText(context, error, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}