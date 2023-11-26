package sisterhood.domain

interface HentaiCache {
    suspend fun readThumbnail(id: HentaiId): HentaiImage?
    suspend fun readPage(id: HentaiId, pageNumber: Int): HentaiImage?
    suspend fun writeThumbnail(id: HentaiId, thumbnail: HentaiImage)
    suspend fun writePage(id: HentaiId, pageNumber: Int, page: HentaiImage)
}
