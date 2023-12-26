package sisterhood.hentai.infrastructure

import sisterhood.hentai.SqliteDriverFactory

class SqlDelightHitomiCacheFactory(
    private val path: String,
    private val sqliteDriverFactory: SqliteDriverFactory
) {
    companion object {
        lateinit var instance: SqlDelightHitomiCache
    }

    fun create(): SqlDelightHitomiCache =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHitomiCache(sqliteDriverFactory.create(HentaiCache.Schema, path))
            instance
        }
}
