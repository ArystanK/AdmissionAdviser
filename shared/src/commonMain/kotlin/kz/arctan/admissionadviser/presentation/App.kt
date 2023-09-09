package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.arctan.admissionadviser.presentation.localization.Strings

@Composable
fun App(
    state: MainState, flatMap: (MainIntent) -> Unit
) {
    if (state.openPageId == 1)
        MaterialTheme {
            Scaffold(bottomBar = {
                RoundedTextFieldWithSendButton(
                    value = state.message,
                    onValueChange = { flatMap(MainIntent.TextChangeMainIntent(it)) },
                    onSendClick = { flatMap(MainIntent.MessageSent) },
                    language = state.language
                )
//            BottomBarWithChat(
//                message = state.message,
//                onMessageChange = { component.flatMap(MainIntent.TextChangeMainIntent(it)) },
//                onMessageSend = { component.flatMap(MainIntent.MessageSent) },
//                language = Strings.Language.RUSSIAN
//            )
            }) {
                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                }
                LazyColumn {
                    items(state.messages) {
                        MessageBubble(it.text, it.from_ai == 0L)
                    }
                }
            }
        }
    else WelcomeView(state, flatMap)
}

