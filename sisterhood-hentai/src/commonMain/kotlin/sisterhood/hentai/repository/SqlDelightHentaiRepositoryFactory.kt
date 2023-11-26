package sisterhood.hentai.repository

import sisterhood.hentai.SqliteDriverFactory

class SqlDelightHentaiRepositoryFactory(
    private val sqliteDriverFactory: SqliteDriverFactory,
    private val path: String?
) {
    companion object {
        lateinit var instance: SqlDelightHentaiRepository
    }

    fun create(): SqlDelightHentaiRepository =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = SqlDelightHentaiRepository(sqliteDriverFactory.create(HentaiDatabase.Schema, path))
            instance
        }
}
