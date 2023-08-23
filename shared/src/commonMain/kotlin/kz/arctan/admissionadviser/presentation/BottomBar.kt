package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kz.arctan.admissionadviser.presentation.localization.Strings

@Composable
fun BottomBar(
    message: String,
    onMessageChange: (String) -> Unit,
    onMessageSend: () -> Unit,
    language: Strings.Language,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = message,
            onValueChange = onMessageChange,
            placeholder = {
                Text(text = Strings[Strings.TYPE_YOUR_MESSAGE_HERE_KEY, language] ?: "")
            }
        )
        IconButton(
            onClick = onMessageSend
        ) {
            Icon(Icons.Default.Send, Strings[Strings.SEND_MESSAGE, language])
        }
    }
}