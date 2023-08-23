package kz.arctan.admissionadviser.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.arctan.admissionadviser.data.db.AdvisorDatabase
import kz.arctan.sqldelight.chat.data.Message
import kotlin.jvm.JvmInline

class MainComponent(
    private val database: AdvisorDatabase
) : Component<MainState, MainIntent> {
    private val _state = MutableStateFlow(MainState())
    override val state = _state.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            database.getAllMessages().collect { messages ->
                _state.update { it.copy(messages = messages) }
            }
        }
    }

    override fun flatMap(intent: MainIntent) {
        when (intent) {
            is MainIntent.TextChangeMainIntent -> _state.update { it.copy(message = intent.text) }
            MainIntent.MessageSent -> {
                _state.update {
                    it.copy(
                        messages = it.messages + Message(
                            id = it.messages.size.toLong(),
                            text = it.message,
                            from_ai = 0L
                        ),
                        message = ""
                    )
                }
            }
        }
    }
}

data class MainState(
    val message: String = "",
    val messages: List<Message> = emptyList(),
)

sealed interface MainIntent {
    @JvmInline
    value class TextChangeMainIntent(val text: String) : MainIntent
    data object MessageSent : MainIntent
}