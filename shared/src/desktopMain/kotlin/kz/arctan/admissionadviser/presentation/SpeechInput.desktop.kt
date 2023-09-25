package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kz.arctan.admissionadviser.presentation.localization.Strings

@Composable
actual fun SpeechInput(state: MainState, flatMap: (MainIntent) -> Unit) {
    IconButton(onClick = { flatMap(MainIntent.MessageSent) }) {
        Icon(
            imageVector = Icons.Default.Send,
            contentDescription = Strings[Strings.SEND_MESSAGE, state.language],
            tint = Color.Gray,
            modifier = Modifier
                .size(36.dp)
        )
    }
}