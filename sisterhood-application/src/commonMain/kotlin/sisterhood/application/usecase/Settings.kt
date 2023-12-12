package sisterhood.application.usecase

import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    val hentaiGridColumns: Int = 2,
    val themeBrightness: ThemeBrightness = ThemeBrightness.LIGHT
)
