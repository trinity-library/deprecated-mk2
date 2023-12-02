package sisterhood.application.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val DEFAULT_SIZE = 16.026f
const val PADDING_SIZE = 2f

operator fun TextUnit.div(other: TextUnit) = if (isSp == other.isSp) {
    value / other.value
} else {
    if (isEm) {
        DEFAULT_SIZE * value / other.value
    } else {
        value / other.value
    }
}

@Composable
fun LoadingText(
    fontSize: TextUnit = DEFAULT_SIZE.sp,
    length: Int = 10,
    animationDuration: Int = 500
) {
    val ratio = fontSize / DEFAULT_SIZE.sp
    val scale by rememberInfiniteTransition("Blink infinitely").animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Blink animation"
    )

    Box(
        modifier = Modifier
            .height((fontSize.value + PADDING_SIZE * 2).dp)
            .padding(vertical = 4.dp)
    ) {
        Canvas(modifier = Modifier) {
            drawRoundRect(
                color = Color(scale, scale, scale),
                size = Size(24f * length * ratio, 46f * ratio),
                cornerRadius = CornerRadius(16f, 16f)
            )
        }
    }
}
