package sisterhood.application.usecase

interface SettingsStore {
    fun load(): Settings
    fun save(settings: Settings): Unit
}
