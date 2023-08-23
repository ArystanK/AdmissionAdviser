package kz.arctan.admissionadviser.presentation

import kotlinx.coroutines.flow.StateFlow

interface Component<State, Intent> {
    val state: StateFlow<State>
    fun flatMap(intent: Intent)
}
