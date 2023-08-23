package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.arctan.admissionadviser.presentation.localization.Strings

@Composable
fun BottomBarWithChat(
    message: String,
    onMessageChange: (String) -> Unit,
    onMessageSend: () -> Unit,
    language: Strings.Language
) {
    Card {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
        ) {
            BasicTextField(
                value = message.ifBlank { Strings[Strings.SEND_MESSAGE, language] ?: "" },
                onValueChange = onMessageChange,
                textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
                modifier = Modifier.weight(1f).padding(end = 16.dp),
                singleLine = true,
                cursorBrush = SolidColor(Color.Black),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Send),
                keyboardActions = KeyboardActions(onSend = { onMessageSend() })
            )

            IconButton(
                onClick = onMessageSend,
                modifier = Modifier.height(40.dp),
            ) {
                Icon(Icons.Default.Send, Strings[Strings.SEND_MESSAGE, language] ?: "")
            }
        }
    }
}
