package sisterhood.application

data class Configuration(
    val cachePath: String = "",
    val databasePath: String = ""
) {
    class Scope {
        lateinit var cachePath: String
        lateinit var databasePath: String

        fun build() = Configuration(cachePath, databasePath)
    }
}
