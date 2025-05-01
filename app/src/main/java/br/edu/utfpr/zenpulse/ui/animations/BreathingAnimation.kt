package br.edu.utfpr.zenpulse.ui.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.tooling.preview.devices.WearDevices

@Composable
fun BreathingAnimation() {
    val scale = rememberInfiniteTransition()
        .animateFloat(
            initialValue = 0.5f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(2000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

    Surface(
        modifier = Modifier
            .size(80.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
    ) {}
}

@Composable
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
fun BreathingAnimationPreview() {
    BreathingAnimation()
}