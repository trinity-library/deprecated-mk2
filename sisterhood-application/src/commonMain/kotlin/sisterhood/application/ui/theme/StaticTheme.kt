package sisterhood.application.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import sisterhood.application.usecase.Settings

@Composable
fun StaticTheme(settings: Settings, content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = hinataRedColorScheme(settings.themeBrightness)
    ) {
        content()
    }
}
