package kz.arctan.admissionadviser.presentation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Component<State, Intent> {
    val mutableState: MutableStateFlow<State>
    val state: StateFlow<State>
    fun flatMap(intent: Intent)
}
