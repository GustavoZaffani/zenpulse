package br.edu.utfpr.zenpulse.shared.utils

object HeartRateThreshold {
    private const val RESTING_BPM_LIMIT = 100f

    fun isElevated(bpm: Float): Boolean {
        return bpm > RESTING_BPM_LIMIT
    }
}