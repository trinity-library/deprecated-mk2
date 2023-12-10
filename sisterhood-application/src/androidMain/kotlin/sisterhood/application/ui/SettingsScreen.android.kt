package sisterhood.application.ui

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sisterhood.application.ui.settings.SettingsComponent
import sisterhood.application.usecase.Settings

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
actual fun SettingsScreen(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
    naviBack: () -> Unit
) {
    BackHandler { naviBack() }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Settings") },
                navigationIcon = {
                    IconButton(onClick = naviBack) {
                        Icon(Icons.Default.ArrowBack, "backIcon")
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            SettingsComponent(settings = settings, changeSettings = changeSettings)
        }
    }
}
