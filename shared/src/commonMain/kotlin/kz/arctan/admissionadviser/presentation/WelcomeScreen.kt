package kz.arctan.admissionadviser.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun WelcomeScreen(state: MainState, flatMap: (MainIntent) -> Unit)
