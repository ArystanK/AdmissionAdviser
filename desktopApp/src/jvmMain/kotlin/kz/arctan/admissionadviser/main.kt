package kz.arctan.admissionadviser

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kz.arctan.admissionadviser.presentation.MainView
import org.koin.compose.koinInject
import org.koin.core.context.GlobalContext.get
import org.koin.core.context.startKoin
import org.koin.logger.slf4jLogger

fun main() {
    startKoin {
        slf4jLogger()
        modules(appModule)
    }
    application {
        Window(onCloseRequest = ::exitApplication, title = "Admission Adviser") {
            MainView(koinInject())
        }
    }
}