package sisterhood.application.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import sisterhood.application.usecase.Settings
import sisterhood.application.usecase.ThemeBrightness

@Composable
fun StaticTheme(settings: Settings, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = when (settings.themeBrightness) {
            ThemeBrightness.LIGHT -> HinataLight
            ThemeBrightness.DARK -> HinataDark
        }
    ) {
        content()
    }
}
