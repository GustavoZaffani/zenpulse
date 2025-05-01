package br.edu.utfpr.zenpulse.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.edu.utfpr.zenpulse.shared.utils.HeartRateThreshold

data class HeartRateUiState(
    val currentHeartRate: Float = 0f,
    val isElevated: Boolean = false,
    val showAlert: Boolean = false
)

class HeartRateViewModel : ViewModel() {

    var uiState: HeartRateUiState by mutableStateOf(HeartRateUiState())

    fun updateHeartRate(bpm: Float) {
        uiState = uiState.copy(
            currentHeartRate = bpm
        )

        val isElevated = HeartRateThreshold.isElevated(bpm)
        if (mustBeUpdateShowAlert(isElevated)) {
            uiState = uiState.copy(
                isElevated = isElevated,
                showAlert = isElevated
            )
        }
    }

    private fun mustBeUpdateShowAlert(isElevated: Boolean): Boolean {
        return (isElevated && !uiState.isElevated) || !isElevated;
    }

    fun dismissAlert() {
        uiState = uiState.copy(
            showAlert = false
        )
    }
}