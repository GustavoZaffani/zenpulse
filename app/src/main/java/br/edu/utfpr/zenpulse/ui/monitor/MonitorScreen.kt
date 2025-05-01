package br.edu.utfpr.zenpulse.ui.monitor


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import androidx.wear.tooling.preview.devices.WearDevices
import br.edu.utfpr.zenpulse.ui.animations.HeartAnimation
import br.edu.utfpr.zenpulse.ui.viewmodel.HeartRateViewModel

@Composable
fun MonitorScreen(
    viewModel: HeartRateViewModel,
    onShowAlert: () -> Unit
) {

    val bpm = viewModel.uiState.currentHeartRate
    val isElevated = viewModel.uiState.isElevated
    val borderColor = if (isElevated) Color(0xFFE31400) else Color(0xFF004400)

    LaunchedEffect(viewModel.uiState.showAlert) {
        if (viewModel.uiState.showAlert) {
            onShowAlert()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(CircleShape)
            .border(BorderStroke(3.dp, borderColor), CircleShape)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeartAnimation()
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = bpm.toInt().toString(),
                style = MaterialTheme.typography.headlineLarge,
                color = if (isElevated) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Bpm",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }


}

@Composable
@Preview(device = WearDevices.SMALL_ROUND, showSystemUi = true)
fun MonitorScreenPreview() {
    MonitorScreen(
        viewModel = HeartRateViewModel(),
        onShowAlert = {}
    )
}