package sisterhood.application

data class Configuration(
    val cachePath: String = "",
    val databasePath: String = ""
) {
    class Scope {
        lateinit var appSpecificPath: String

        fun build() = Configuration(
            appSpecificPath.plus("/.cache.db"),
            appSpecificPath.plus("/.database.db")
        )
    }
}
