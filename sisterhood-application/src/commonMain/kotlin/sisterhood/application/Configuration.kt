package sisterhood.application

data class Configuration(
    val cachePath: String,
    val databasePath: String
) {
    class Scope {
        private lateinit var cachePathHolder: String
        var cachePath: String
            get() {
                return cachePathHolder
            }
            set(value) {
                cachePathHolder = value
            }

        private lateinit var databasePathHolder: String
        var databasePath: String
            get() {
                return databasePathHolder
            }
            set(value) {
                databasePathHolder = value
            }

        fun build() = Configuration(cachePath, databasePath)
    }
}
