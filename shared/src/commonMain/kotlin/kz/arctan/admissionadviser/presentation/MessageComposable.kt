package kz.arctan.admissionadviser.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun MessageBubble(
    message: String,
    isSentByUser: Boolean,
    bubbleColor: Color = if (isSentByUser) Color.Blue else Color.Gray,
    tailColor: Color = bubbleColor,
    cornerRadius: Dp = 16.dp,
    tailHeight: Dp = 20.dp,
    tailWidth: Dp = 12.dp,
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
                .clip(RoundedCornerShape(cornerRadius))
                .background(bubbleColor)
                .padding(16.dp)
        ) {
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.White
            )
        }

        // Tail
        Spacer(
            modifier = Modifier
                .size(tailHeight, tailWidth)
                .clip(TriangularShape(isSentByUser))
                .background(tailColor)
        )
    }
}

@Composable
private fun TriangularShape(isSentByUser: Boolean): Shape {
    return object : Shape {
        override fun createOutline(
            size: Size,
            layoutDirection: LayoutDirection,
            density: Density
        ): Outline {
            val path = Path().apply {
                if (isSentByUser) {
                    moveTo(size.width, size.height)
                    lineTo(size.width, 0f)
                    lineTo(0f, 0f)
                } else {
                    moveTo(0f, size.height)
                    lineTo(0f, 0f)
                    lineTo(size.width, 0f)
                }
                close()
            }
            return Outline.Generic(path)
        }
    }
}
