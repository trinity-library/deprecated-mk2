package sisterhood.hentai.business

import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

interface HitomiCache {
    suspend fun readThumbnail(id: HentaiId): HentaiImage?
    suspend fun readPage(id: HentaiId, number: Int): HentaiImage?
    suspend fun writeThumbnail(id: HentaiId, thumbnail: HentaiImage)
    suspend fun writePage(id: HentaiId, number: Int, page: HentaiImage)
}
