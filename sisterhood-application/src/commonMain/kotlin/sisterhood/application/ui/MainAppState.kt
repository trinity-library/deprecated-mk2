package sisterhood.application.ui

import androidx.compose.runtime.*
import sisterhood.application.Dependency
import sisterhood.application.usecase.Settings
import sisterhood.application.usecase.SettingsStore

@Composable
fun rememberMainAppState() = remember { MainAppState() }

class MainAppState(
    private val settingsStore: SettingsStore = Dependency.provideSettingsStore()
) {
    var settings by mutableStateOf(settingsStore.load())

    fun changeSettings(settings: Settings) {
        settingsStore.save(settings)
        this.settings = settings
    }
}
