package br.edu.utfpr.zenpulse.ui.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun HeartAnimation() {
    val scale = rememberInfiniteTransition()
        .animateFloat(
            initialValue = 0.7f,
            targetValue = 1.5f,
            animationSpec = infiniteRepeatable(
                animation = tween(1500, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

    Icon(
        imageVector = Icons.Default.Favorite,
        contentDescription = "Heart Icon",
        tint = Color.Red,
        modifier = Modifier
            .graphicsLayer (
                scaleX = scale.value,
                scaleY = scale.value
            )
    )
}

@Composable
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
fun HeartAnimationPreview() {
    HeartAnimation()
}