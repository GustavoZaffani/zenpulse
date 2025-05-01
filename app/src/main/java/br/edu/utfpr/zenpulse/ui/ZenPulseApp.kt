package br.edu.utfpr.zenpulse.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import br.edu.utfpr.zenpulse.ui.alert.AlertScreen
import br.edu.utfpr.zenpulse.ui.breathing.BreathingScreen
import br.edu.utfpr.zenpulse.ui.feedback.FeedbackScreen
import br.edu.utfpr.zenpulse.ui.monitor.MonitorScreen
import br.edu.utfpr.zenpulse.ui.viewmodel.HeartRateViewModel

object Screens {
    const val MONITOR = "monitor"
    const val ALERT = "alert"
    const val FEEDBACK = "feedback"
    const val BREATHING = "breathing"
}

@Composable
fun ZenPulseApp(viewModel: HeartRateViewModel) {
    var currentScreen by remember { mutableStateOf(Screens.MONITOR) }

    when (currentScreen) {
        Screens.MONITOR -> MonitorScreen(
            viewModel = viewModel,
            onShowAlert = { currentScreen = Screens.ALERT }
        )

        Screens.ALERT -> AlertScreen(
            onStartBreathing = {
                viewModel.dismissAlert()
                currentScreen = Screens.BREATHING
            }
        )

        Screens.BREATHING -> BreathingScreen(
            onFinish = { currentScreen = Screens.FEEDBACK }
        )

        Screens.FEEDBACK -> FeedbackScreen(
            onBack = { currentScreen = Screens.MONITOR }
        )
    }
}