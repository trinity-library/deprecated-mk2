package sisterhood.application.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import sisterhood.application.usecase.ThemeBrightness

fun hinataRedColorScheme(themeBrightness: ThemeBrightness) = when (themeBrightness) {
    ThemeBrightness.LIGHT -> lightColorScheme()
    ThemeBrightness.DARK -> darkColorScheme()
}
