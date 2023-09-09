package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp

@Composable
fun MessageBubble(
    message: String,
    isSentByUser: Boolean,
    bubbleColor: Color = if (isSentByUser) Color.Blue else Color.Gray,
    cornerRadius: Dp = 16.dp,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        contentAlignment = if (isSentByUser) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        // Message bubble with a tail
        Column(
            horizontalAlignment = if (isSentByUser) Alignment.End else Alignment.Start,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = cornerRadius,
                        bottomEnd = if (!isSentByUser) cornerRadius else 0.dp,
                        bottomStart = if (isSentByUser) cornerRadius else 0.dp
                    )
                )
                .background(bubbleColor)
                .padding(16.dp)
        ) {
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}