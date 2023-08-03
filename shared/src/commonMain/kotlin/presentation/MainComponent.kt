package presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.jvm.JvmInline

class MainComponent {
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun flatMap(mainIntent: MainIntent) {
        when (mainIntent) {
            is MainIntent.TextChangeMainIntent -> _state.update { it.copy(message = mainIntent.text) }
            MainIntent.MessageSent -> TODO()
        }
    }
}

data class MainState(
    val message: String = "",
    val messages: List<String> = emptyList(),
)

sealed interface MainIntent {
    @JvmInline
    value class TextChangeMainIntent(val text: String) : MainIntent
    data object MessageSent : MainIntent
}