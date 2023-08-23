package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MessageComposable(text: String) {
    Box(
        modifier = Modifier
            .background(Color(0xFFF5F5F5))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier.background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(6.dp),
                        clip = true,
                    )
                    .padding(12.dp),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
