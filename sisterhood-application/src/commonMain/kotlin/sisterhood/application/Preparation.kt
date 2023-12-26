package sisterhood.application

import sisterhood.application.driver.OkioSettingsStoreFactory
import sisterhood.hentai.SqliteDriverFactory
import sisterhood.hentai.infrastructure.KtorHitomiClientFactory
import sisterhood.hentai.infrastructure.SqlDelightHitomiCacheFactory
import sisterhood.hentai.infrastructure.SqlDelightHitomiRepositoryFactory

data class Preparation(
    val clientFactory: KtorHitomiClientFactory,
    val cacheFactory: SqlDelightHitomiCacheFactory,
    val repositoryFactory: SqlDelightHitomiRepositoryFactory,
    val storeFactory: OkioSettingsStoreFactory,
) {
    class Scope(private val configuration: Configuration = Configuration()) {
        lateinit var sqliteDriverFactory: SqliteDriverFactory

        fun build() = Preparation(
            KtorHitomiClientFactory(),
            SqlDelightHitomiCacheFactory(configuration.cachePath, sqliteDriverFactory),
            SqlDelightHitomiRepositoryFactory(configuration.databasePath, sqliteDriverFactory),
            OkioSettingsStoreFactory(configuration.settingsPath)
        )
    }
}
