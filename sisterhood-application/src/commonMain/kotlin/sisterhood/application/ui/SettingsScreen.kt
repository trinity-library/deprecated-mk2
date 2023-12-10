package sisterhood.application.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sisterhood.application.ui.settings.SettingsComponent
import sisterhood.application.usecase.Settings

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
    naviBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    IconButton(onClick = naviBack) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }
            )
        }
    ) {
        SettingsComponent(settings = settings, changeSettings = changeSettings)
    }
}
