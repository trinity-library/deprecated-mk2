package sisterhood.hentai.cache

import app.cash.sqldelight.db.SqlDriver
import sisterhood.domain.HentaiCache
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

class SqlDelightHentaiCache(sqliteDriver: SqlDriver) : HentaiCache {
    private val db = HentaiCache(driver = sqliteDriver)

    override suspend fun readThumbnail(id: HentaiId): HentaiImage? = try {
        db.thumbnailCacheQueries.select(id).executeAsOneOrNull()?.thumbnail
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }

    override suspend fun readPage(id: HentaiId, pageNumber: Int): HentaiImage? = try {
        db.pageCacheQueries.select(id, pageNumber.toLong()).executeAsOneOrNull()?.page
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }

    override suspend fun writeThumbnail(id: HentaiId, thumbnail: HentaiImage) = try {
        db.thumbnailCacheQueries.insert(id, thumbnail)
    } catch (t: Throwable) {
        t.printStackTrace()
    }

    override suspend fun writePage(id: HentaiId, pageNumber: Int, page: HentaiImage) = try {
        db.pageCacheQueries.insert(id, pageNumber.toLong(), page)
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}
