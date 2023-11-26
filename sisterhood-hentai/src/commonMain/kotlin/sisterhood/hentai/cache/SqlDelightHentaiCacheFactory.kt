package sisterhood.hentai.cache

import app.cash.sqldelight.db.SqlDriver

class SqlDelightHentaiCacheFactory(private val sqliteDriver: SqlDriver) {
    companion object {
        lateinit var instance: SqlDelightHentaiCache
    }

    fun create(): SqlDelightHentaiCache =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHentaiCache(sqliteDriver)
            instance
        }
}
