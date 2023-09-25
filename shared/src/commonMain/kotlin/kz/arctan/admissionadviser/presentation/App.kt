package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kz.arctan.admissionadviser.presentation.localization.Strings
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(
    state: MainState, flatMap: (MainIntent) -> Unit
) {
    if (state.openPageId == 1)
        MaterialTheme {
            Scaffold(
                bottomBar = {
                    RoundedTextFieldWithSendButton(state, flatMap)
//            BottomBarWithChat(
//                message = state.message,
//                onMessageChange = { component.flatMap(MainIntent.TextChangeMainIntent(it)) },
//                onMessageSend = { component.flatMap(MainIntent.MessageSent) },
//                language = Strings.Language.RUSSIAN
//            )
                },
                topBar = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Image(
                            painterResource("drawable/astana_it_logo.png"),
                            "Astana IT logo",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.width(114.dp).height(62.dp).padding(8.dp)
                        )
                        Row {
                            Row {
                                TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.ENGLISH)) }) {
                                    Text(Strings[Strings.ENGLISH, state.language] ?: "", color = Color.Black)
                                }
//                        TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.KAZAKH)) }) {
//                            Text(Strings[Strings.KAZAKH, state.language] ?: "", color = Color.Black)
//                        }
                                TextButton(onClick = { flatMap(MainIntent.LanguageChangeMainIntent(Strings.Language.RUSSIAN)) }) {
                                    Text(Strings[Strings.RUSSIAN, state.language] ?: "", color = Color.Black)
                                }
                            }
                        }
                    }
                }
            ) {
                if (state.isLoading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(modifier = Modifier.size(50.dp))
                    }
                }
                LazyColumn(modifier = Modifier.padding(bottom = 50.dp).padding(horizontal = 20.dp)) {
                    items(state.messages) {
                        MessageBubble(it.text, it.from_ai == 0L)
                    }
                }
            }
        }
    else WelcomeScreen(state, flatMap)
}

