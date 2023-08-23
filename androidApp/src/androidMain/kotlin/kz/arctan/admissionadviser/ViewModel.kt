package kz.arctan.admissionadviser

import androidx.lifecycle.ViewModel
import kz.arctan.admissionadviser.presentation.Component
import kz.arctan.admissionadviser.presentation.MainComponent
import kz.arctan.admissionadviser.presentation.MainIntent
import kz.arctan.admissionadviser.presentation.MainState


class MainViewModel(
    private val mainComponent: MainComponent,
) : ViewModel(), Component<MainState, MainIntent> by mainComponent