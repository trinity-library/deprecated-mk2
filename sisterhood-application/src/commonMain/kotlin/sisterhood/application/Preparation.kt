package sisterhood.application

import sisterhood.hentai.cache.SqlDelightHentaiCacheFactory
import sisterhood.hentai.repository.SqlDelightHentaiRepositoryFactory
import sisterhood.hentai.service.hitomi.HitomiServiceFactory

data class Preparation(
    val cacheFactory: SqlDelightHentaiCacheFactory,
    val repositoryFactory: SqlDelightHentaiRepositoryFactory,
    val serviceFactory: HitomiServiceFactory
) {
    class Scope {

    }
}
