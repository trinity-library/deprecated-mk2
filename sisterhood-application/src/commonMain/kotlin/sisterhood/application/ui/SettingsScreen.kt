package sisterhood.application.ui

import androidx.compose.runtime.Composable
import sisterhood.application.usecase.Settings

@Composable
expect fun SettingsScreen(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
    naviBack: () -> Unit
)
