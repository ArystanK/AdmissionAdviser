package kz.arctan.admissionadviser

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.update
import kz.arctan.admissionadviser.presentation.Component
import kz.arctan.admissionadviser.presentation.MainComponent
import kz.arctan.admissionadviser.presentation.MainIntent
import kz.arctan.admissionadviser.presentation.MainState
import kz.arctan.admissionadviser.presentation.localization.Strings
import java.util.*


class MainViewModel(
    private val mainComponent: MainComponent,
    private val speechRecognizer: SpeechRecognizer
) : ViewModel(), Component<MainState, MainIntent> by mainComponent {

    companion object {
        private const val TAG = "MAIN_VIEW_MODEL"
    }

    private val speechRecognitionIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        putExtra(
            RecognizerIntent.EXTRA_LANGUAGE, when (state.value.language) {
                Strings.Language.KAZAKH -> Locale("kz", "KZ")
                Strings.Language.RUSSIAN -> Locale("ru", "KZ")
                Strings.Language.ENGLISH -> Locale.ENGLISH
            }
        )
    }


    override fun flatMap(intent: MainIntent) {
        mainComponent.flatMap(intent)
        val speechRecognitionListener = object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}

            override fun onBeginningOfSpeech() {
                Log.d(TAG, "onBeginningOfSpeech: Started")
            }

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {}

            override fun onResults(results: Bundle?) {
                val result = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)?.get(0)
                Log.d(TAG, "onResults: $result")
                mutableState.update {
                    it.copy(
                        isSpeechListened = false,
                        message = result ?: ""
                    )
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        }
        speechRecognizer.setRecognitionListener(speechRecognitionListener)

        when (intent) {
            MainIntent.StartSpeechListening -> {
                mutableState.update { it.copy(isSpeechListened = true) }
                speechRecognizer.startListening(speechRecognitionIntent)
            }

            MainIntent.EndSpeechListening -> {
                speechRecognizer.stopListening()
            }

            MainIntent.CancelSpeechListening -> {
                speechRecognizer.cancel()
            }

            else -> {}
        }
    }
}