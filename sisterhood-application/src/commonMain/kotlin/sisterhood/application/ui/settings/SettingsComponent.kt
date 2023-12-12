package sisterhood.application.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sisterhood.application.usecase.Settings
import sisterhood.application.usecase.ThemeBrightness

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsComponent(
    settings: Settings,
    changeSettings: (Settings) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        val hentaiGridColumnsOptions = (2..4).toList()
        DropdownSetting(
            options = hentaiGridColumnsOptions,
            title = "Hentai Grid Columns",
            label = "Columns",
            initialSelectedIndex = hentaiGridColumnsOptions.indexOf(settings.hentaiGridColumns),
            changeSetting = { changeSettings(settings.copy(hentaiGridColumns = it)) }
        )

        val themeBrightnessOptions = ThemeBrightness.entries
        DropdownSetting(
            options = themeBrightnessOptions,
            title = "Theme Brightness",
            initialSelectedIndex = themeBrightnessOptions.indexOf(settings.themeBrightness),
            changeSetting = { changeSettings(settings.copy(themeBrightness = it)) }
        )
    }
}
