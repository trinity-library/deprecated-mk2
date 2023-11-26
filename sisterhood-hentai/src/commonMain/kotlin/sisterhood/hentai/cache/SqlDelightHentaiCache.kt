package sisterhood.hentai.cache

import app.cash.sqldelight.db.SqlDriver
import sisterhood.domain.HentaiCache
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

class SqlDelightHentaiCache(sqliteDriver: SqlDriver) : HentaiCache {
    private val db = HentaiCache(driver = sqliteDriver)

    override suspend fun readThumbnail(id: HentaiId): HentaiImage? =
        db.thumbnailCacheQueries.select(id).executeAsOneOrNull()?.thumbnail

    override suspend fun readPage(id: HentaiId, pageNumber: Int): HentaiImage? =
        db.pageCacheQueries.select(id, pageNumber.toLong()).executeAsOneOrNull()?.page

    override suspend fun writeThumbnail(id: HentaiId, thumbnail: HentaiImage) =
        db.thumbnailCacheQueries.insert(id, thumbnail)

    override suspend fun writePage(id: HentaiId, pageNumber: Int, page: HentaiImage) =
        db.pageCacheQueries.insert(id, pageNumber.toLong(), page)
}
