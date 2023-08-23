package kz.arctan.admissionadviser

import kz.arctan.admissionadviser.data.db.AdvisorDatabase
import kz.arctan.admissionadviser.data.db.DriverFactory
import kz.arctan.admissionadviser.data.db.createDatabase
import kz.arctan.admissionadviser.presentation.MainComponent
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { DriverFactory(androidApplication()) }
    single { createDatabase(get()) }
    single { AdvisorDatabase(get()) }
    single { MainComponent(get()) }
//    scope<MainActivity> { scoped { MainComponent() } }
    viewModel { MainViewModel(get()) }
}