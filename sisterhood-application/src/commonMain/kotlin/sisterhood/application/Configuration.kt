package sisterhood.application

data class Configuration(
    val cachePath: String = "",
    val databasePath: String = "",
    val settingsPath: String = "",
) {
    class Scope {
        lateinit var appCachePath: String
        lateinit var appFilesPath: String

        fun build() = Configuration(
            appCachePath.plus("/.cache.db"),
            appFilesPath.plus("/.database.db"),
            appFilesPath.plus("/.settings.json")
        )
    }
}
