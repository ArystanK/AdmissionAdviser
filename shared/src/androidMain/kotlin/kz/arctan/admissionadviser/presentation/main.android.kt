package kz.arctan.admissionadviser.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainView(state: MainState, flatMap: (MainIntent) -> Unit) = App(state, flatMap)

