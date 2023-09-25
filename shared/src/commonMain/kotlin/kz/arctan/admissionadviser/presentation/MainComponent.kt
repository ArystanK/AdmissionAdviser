package kz.arctan.admissionadviser.presentation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kz.arctan.admissionadviser.data.db.AdvisorDatabase
import kz.arctan.admissionadviser.data.remote.MessageService
import kz.arctan.admissionadviser.presentation.localization.Strings
import kz.arctan.sqldelight.chat.data.Answer
import kz.arctan.sqldelight.chat.data.Message
import kotlin.jvm.JvmInline


class MainComponent(
    private val database: AdvisorDatabase,
    private val service: MessageService,
) : Component<MainState, MainIntent> {
    override val mutableState = MutableStateFlow(MainState())
    override val state = mutableState.asStateFlow()

    init {
        mutableState.update { it.copy(isLoading = true) }
        CoroutineScope(Dispatchers.IO).launch {
            launch {
                database.getAllAnswers().collect { answers ->
                    mutableState.update { it.copy(answers = answers) }
                }
            }
            launch {
                database.getAllMessages().collect { messages ->
                    mutableState.update { it.copy(messages = messages) }
                }
            }
        }.invokeOnCompletion {
            mutableState.update { it.copy(isLoading = false) }
        }
    }

    override fun flatMap(intent: MainIntent) {
        when (intent) {
            is MainIntent.TextChangeMainIntent -> mutableState.update { it.copy(message = intent.text) }
            MainIntent.MessageSent -> {
                if (mutableState.value.message.isBlank()) return
                mutableState.update { innerState ->
                    innerState.copy(
                        messages = innerState.messages + Message(
                            id = innerState.messages.size.toLong(),
                            text = innerState.message,
                            from_ai = 0L
                        ),
                        message = "",
                        isLoading = true
                    )
                }
                CoroutineScope(Dispatchers.Default).launch {
                    launch { database.sentMessage(mutableState.value.messages.last().text, false) }
                    val deferredAnswer = async {
                        try {
                            service.sendMessage(mutableState.value.messages.last().text)
                        } catch (e: Exception) {
                            e.printStackTrace()
                            null
                        }
                    }
                    val answer = deferredAnswer.await()
                    answer?.let { launch { database.sentMessage(it, true) } }
                    mutableState.update {
                        it.copy(
                            isLoading = false,
                            messages = it.messages + Message(
                                id = it.messages.size.toLong(),
                                text = answer ?: "I don't know",
                                from_ai = 1L
                            )
                        )
                    }
                }
            }

            is MainIntent.LanguageChangeMainIntent -> mutableState.update { it.copy(language = intent.language) }
            is MainIntent.OpenPageIdChangeMainIntent -> mutableState.update { it.copy(openPageId = intent.openPageId) }
            else -> {}
        }
    }

    private fun levenshteinDistance(str1: String, str2: String): Int {
        val m = str1.length
        val n = str2.length
        val dp = Array(m + 1) { IntArray(n + 1) }

        for (i in 0..m) {
            for (j in 0..n) {
                when {
                    i == 0 -> dp[i][j] = j
                    j == 0 -> dp[i][j] = i
                    else -> {
                        val cost = if (str1[i - 1] == str2[j - 1]) 0 else 1
                        dp[i][j] = minOf(dp[i - 1][j] + 1, dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost)
                    }
                }
            }
        }

        return dp[m][n]
    }
}

data class MainState(
    val message: String = "",
    val messages: List<Message> = emptyList(),
    val openPageId: Int = 0,
    val language: Strings.Language = Strings.Language.ENGLISH,
    val answers: List<Answer> = emptyList(),
    val isLoading: Boolean = false,
    val isSpeechListened: Boolean = false
)

sealed interface MainIntent {
    @JvmInline
    value class TextChangeMainIntent(val text: String) : MainIntent
    data object MessageSent : MainIntent

    @JvmInline
    value class LanguageChangeMainIntent(val language: Strings.Language) : MainIntent

    @JvmInline
    value class OpenPageIdChangeMainIntent(val openPageId: Int) : MainIntent

    data object StartSpeechListening : MainIntent

    data object EndSpeechListening : MainIntent

    data object CancelSpeechListening : MainIntent
}