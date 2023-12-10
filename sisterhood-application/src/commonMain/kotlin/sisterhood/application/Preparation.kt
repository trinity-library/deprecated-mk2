package sisterhood.application

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import sisterhood.application.driver.OkioSettingsStoreFactory
import sisterhood.hentai.SqliteDriverFactory
import sisterhood.hentai.cache.SqlDelightHentaiCacheFactory
import sisterhood.hentai.repository.SqlDelightHentaiRepositoryFactory
import sisterhood.hentai.service.hitomi.HitomiServiceFactory

data class Preparation(
    val cacheFactory: SqlDelightHentaiCacheFactory,
    val repositoryFactory: SqlDelightHentaiRepositoryFactory,
    val serviceFactory: HitomiServiceFactory,
    val storeFactory: OkioSettingsStoreFactory,
) {
    class Scope(private val configuration: Configuration = Configuration()) {
        lateinit var cacheSchema: SqlSchema<QueryResult.AsyncValue<Unit>>
        lateinit var databaseSchema: SqlSchema<QueryResult.AsyncValue<Unit>>
        lateinit var sqliteDriverFactory: SqliteDriverFactory
        lateinit var serviceFactory: HitomiServiceFactory

        fun build() = Preparation(
            SqlDelightHentaiCacheFactory(configuration.cachePath, cacheSchema, sqliteDriverFactory),
            SqlDelightHentaiRepositoryFactory(configuration.databasePath, databaseSchema, sqliteDriverFactory),
            HitomiServiceFactory(),
            OkioSettingsStoreFactory(configuration.settingsPath)
        )
    }
}
