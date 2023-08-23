package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kz.arctan.admissionadviser.presentation.localization.Strings

@Composable
fun App(
    component: Component<MainState, MainIntent>
) {
    val state by component.state.collectAsState()
    MaterialTheme {
        Scaffold(bottomBar = {
            BottomBarWithChat(
                message = state.message,
                onMessageChange = { component.flatMap(MainIntent.TextChangeMainIntent(it)) },
                onMessageSend = { component.flatMap(MainIntent.MessageSent) },
                language = Strings.Language.RUSSIAN
            )
        }) {
            LazyColumn {
                items(state.messages) {
                    MessageBubble(it.text, it.from_ai == 0L)
                }
            }
        }
    }
}

