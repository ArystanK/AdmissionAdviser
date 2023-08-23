package kz.arctan.admissionadviser.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.jvm.JvmInline

class MainComponent : Component<MainState, MainIntent> {
    private val _state = MutableStateFlow(MainState())
    override val state = _state.asStateFlow()

    override fun flatMap(intent: MainIntent) {
        when (intent) {
            is MainIntent.TextChangeMainIntent -> _state.update { it.copy(message = intent.text) }
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