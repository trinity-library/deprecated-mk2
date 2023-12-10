package sisterhood.application.driver

import okio.FileSystem

class OkioSettingsStoreFactory(private val path: String) {
    fun create(): OkioSettingsStore {
        val fileSystem = FileSystem.SYSTEM
        return OkioSettingsStore(fileSystem, path)
    }
}
