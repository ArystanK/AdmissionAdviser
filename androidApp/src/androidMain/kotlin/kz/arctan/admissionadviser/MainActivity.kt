package kz.arctan.admissionadviser

import kz.arctan.admissionadviser.presentation.MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.compose.getViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = getViewModel<MainViewModel>()
            MainView(viewModel)
        }
    }
}