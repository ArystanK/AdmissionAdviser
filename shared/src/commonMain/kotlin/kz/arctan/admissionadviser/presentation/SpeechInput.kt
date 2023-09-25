package kz.arctan.admissionadviser.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun SpeechInput(state: MainState, flatMap: (MainIntent) -> Unit)