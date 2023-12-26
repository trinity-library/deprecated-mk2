package sisterhood.hentai.infrastructure

import app.cash.sqldelight.db.SqlDriver
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage
import sisterhood.hentai.business.HitomiCache

class SqlDelightHitomiCache(sqlDriver: SqlDriver) : HitomiCache {
    override suspend fun readPage(id: HentaiId, number: Int): HentaiImage? {
        TODO("Not yet implemented")
    }

    override suspend fun readThumbnail(id: HentaiId): HentaiImage? {
        TODO("Not yet implemented")
    }

    override suspend fun writePage(id: HentaiId, number: Int, page: HentaiImage) {
        TODO("Not yet implemented")
    }

    override suspend fun writeThumbnail(id: HentaiId, thumbnail: HentaiImage) {
        TODO("Not yet implemented")
    }
}
