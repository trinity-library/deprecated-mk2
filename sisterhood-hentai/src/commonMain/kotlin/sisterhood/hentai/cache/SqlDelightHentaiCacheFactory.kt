package sisterhood.hentai.cache

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import sisterhood.hentai.SqliteDriverFactory

class SqlDelightHentaiCacheFactory(
    private val path: String,
    private val schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    private val sqliteDriverFactory: SqliteDriverFactory
) {
    companion object {
        lateinit var instance: SqlDelightHentaiCache
    }

    fun create(): SqlDelightHentaiCache =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHentaiCache(sqliteDriverFactory.create(schema, path))
            instance
        }
}
