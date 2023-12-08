package sisterhood.application

data class Configuration(
    val cachePath: String = "",
    val databasePath: String = ""
) {
    class Scope {
        lateinit var appCachePath: String
        lateinit var appDatabasePath: String

        fun build() = Configuration(
            appCachePath.plus("/.cache.db"),
            appDatabasePath.plus("/.database.db")
        )
    }
}
