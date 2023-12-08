package sisterhood.application.driver

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okio.FileNotFoundException
import okio.FileSystem
import okio.Path.Companion.toPath
import sisterhood.application.usecase.Settings
import sisterhood.application.usecase.SettingsStore

class OkioSettingsStore(private val fileSystem: FileSystem, settingsPath: String) : SettingsStore {
    private val settingsPath = settingsPath.toPath()

    override fun load(): Settings = try {
        Json.decodeFromString<Settings>(fileSystem.read(settingsPath) { readUtf8() })
    } catch (_: FileNotFoundException) {
        Settings()
    }

    override fun save(settings: Settings): Unit =
        fileSystem.write(settingsPath) {
            writeUtf8(Json.encodeToString(settings))
        }
}
