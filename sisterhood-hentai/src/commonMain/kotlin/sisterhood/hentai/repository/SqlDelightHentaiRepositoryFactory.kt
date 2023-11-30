package sisterhood.hentai.repository

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import sisterhood.hentai.SqliteDriverFactory

class SqlDelightHentaiRepositoryFactory(
    private val path: String,
    private val schema: SqlSchema<QueryResult.AsyncValue<Unit>>,
    private val sqliteDriverFactory: SqliteDriverFactory,

    ) {
    companion object {
        lateinit var instance: SqlDelightHentaiRepository
    }

    fun create(): SqlDelightHentaiRepository =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHentaiRepository(sqliteDriverFactory.create(schema, path))
            instance
        }
}
