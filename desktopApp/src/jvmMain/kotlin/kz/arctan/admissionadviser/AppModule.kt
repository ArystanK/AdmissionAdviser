package kz.arctan.admissionadviser

import kz.arctan.admissionadviser.data.db.DriverFactory
import kz.arctan.admissionadviser.data.db.createDatabase
import kz.arctan.admissionadviser.presentation.MainComponent
import org.koin.dsl.module

val appModule = module {
    single { DriverFactory() }
    single { createDatabase(get()) }
    single { MainComponent() }
}