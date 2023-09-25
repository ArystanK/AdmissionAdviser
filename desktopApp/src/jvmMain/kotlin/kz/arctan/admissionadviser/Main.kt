package kz.arctan.admissionadviser

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kz.arctan.admissionadviser.presentation.MainComponent
import kz.arctan.admissionadviser.presentation.MainView
import kz.arctan.admissionadviser.presentation.localization.Strings
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(appModule)
    }
    application {
        val component = koinInject<MainComponent>()
        val state by component.state.collectAsState()
        Window(
            onCloseRequest = ::exitApplication,
            title = Strings[Strings.ADMISSION_ADVISER, state.language],
            state = rememberWindowState(size = DpSize(1150.dp, 600.dp)),
            resizable = false
        ) {
            MainView(state, component::flatMap)
        }
    }
}