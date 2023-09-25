package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RoundedTextFieldWithSendButton(
    state: MainState,
    flatMap: (MainIntent) -> Unit
) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)) {
        Row(
            modifier = Modifier.background(Color.LightGray, shape = CircleShape).padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BasicTextField(
                value = state.message,
                onValueChange = { flatMap(MainIntent.TextChangeMainIntent(it)) },
                textStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Start),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Text,
                    autoCorrect = true,
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .onKeyEvent {
                        if (it.key == Key.Enter && it.isShiftPressed) {
                            flatMap(MainIntent.MessageSent)
                            true
                        } else false
                    }
            )

            Spacer(modifier = Modifier.width(8.dp))
            if (state.message.isBlank())
                SpeechInput(state, flatMap)
            else
                IconButton(onClick = { flatMap(MainIntent.MessageSent) }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = Strings[Strings.SEND_MESSAGE, state.language],
                        tint = Color.Gray,
                        modifier = Modifier.size(36.dp)
                    )
                }
        }
        Box(contentAlignment = Alignment.CenterStart) {
            if (state.message.isBlank()) {
                Text(
                    text = Strings[Strings.TYPE_YOUR_MESSAGE_HERE_KEY, state.language],
                    color = Color.Gray,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
        }
    }
}