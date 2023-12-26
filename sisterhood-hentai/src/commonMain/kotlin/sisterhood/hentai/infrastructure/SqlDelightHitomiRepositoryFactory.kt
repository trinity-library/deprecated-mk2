package sisterhood.hentai.infrastructure

import sisterhood.hentai.SqliteDriverFactory

class SqlDelightHitomiRepositoryFactory(
    private val path: String,
    private val sqliteDriverFactory: SqliteDriverFactory
) {
    companion object {
        lateinit var instance: SqlDelightHitomiRepository
    }

    fun create(): SqlDelightHitomiRepository =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHitomiRepository(sqliteDriverFactory.create(HentaiDatabase.Schema, path))
            instance
        }
}
