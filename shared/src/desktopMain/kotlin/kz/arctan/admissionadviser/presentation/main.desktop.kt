package kz.arctan.admissionadviser.presentation

import androidx.compose.runtime.Composable


@Composable
fun MainView(state: MainState, flatMap: (MainIntent) -> Unit) = App(state, flatMap)

