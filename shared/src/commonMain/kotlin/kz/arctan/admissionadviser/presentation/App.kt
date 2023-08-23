package kz.arctan.admissionadviser.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun App(component: Component<MainState, MainIntent>) {
    MaterialTheme {
        MessageComposable("Hello!")
    }
}

