/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter to find the
 * most up to date changes to the libraries and their usages.
 */

package br.edu.utfpr.zenpulse

import android.Manifest.permission.BODY_SENSORS
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat
import br.edu.utfpr.zenpulse.shared.listeners.HeartRateSensorManager
import br.edu.utfpr.zenpulse.ui.viewmodel.HeartRateViewModel
import br.edu.utfpr.zenpulse.ui.ZenPulseApp

class MainActivity : ComponentActivity() {

    private lateinit var heartRateSensorManager: HeartRateSensorManager
    private lateinit var viewModel: HeartRateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(android.R.style.Theme_DeviceDefault)

        if (ContextCompat.checkSelfPermission(this, BODY_SENSORS) != PERMISSION_GRANTED) {
            requestPermissions(arrayOf(BODY_SENSORS), 1)
        }

        viewModel = HeartRateViewModel()

        heartRateSensorManager = HeartRateSensorManager(this) { bpm ->
            viewModel.updateHeartRate(bpm)
        }

        heartRateSensorManager.startListening()

        setContent {
            ZenPulseApp(viewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        heartRateSensorManager.stopListening()
    }
}