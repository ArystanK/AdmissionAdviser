package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.tooling.preview.Preview

@Composable
actual fun SpeechInput(state: MainState, flatMap: (MainIntent) -> Unit) {
    var pressedNumber by remember { mutableStateOf(0) }
    IconButton(
        onClick = {
            if (pressedNumber % 2 == 0)
                flatMap(MainIntent.StartSpeechListening)
            else flatMap(MainIntent.EndSpeechListening)
            pressedNumber++
        },
    ) {
        Icon(
            Icons.Default.Mic,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SpeechInputPreview() {
    SpeechInput(MainState()) {}
}