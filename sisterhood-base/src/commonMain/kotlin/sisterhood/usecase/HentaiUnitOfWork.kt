package sisterhood.usecase

import sisterhood.domain.HentaiCache
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiRepository
import sisterhood.domain.HentaiService

class HentaiUnitOfWork(
    private val hentaiCache: HentaiCache,
    private val hentaiRepository: HentaiRepository,
    private val hentaiService: HentaiService
) {
    suspend fun selectOrFetchAndInsertHentai(id: HentaiId): HentaiInfo? = (
        hentaiRepository.select(id).firstOrNull() ?: hentaiService.fetchHentai(id)
            .onSuccess { it?.also { hentai -> hentaiRepository.insert(hentai) } }
            .getOrNull()
        )?.let { HentaiInfo(it) }

    suspend fun readOrFetchAndWriteThumbnail(id: HentaiId): HentaiThumbnailInfo? = (
        hentaiCache.readThumbnail(id) ?: hentaiService.fetchThumbnail(id)
            .onSuccess { it?.also { thumbnail -> hentaiCache.writeThumbnail(id, thumbnail) } }
            .getOrNull()
        )?.let { HentaiThumbnailInfo(id, it) }

    suspend fun readOrFetchAndWritePage(id: HentaiId, number: Int): HentaiPageInfo? = (
        hentaiCache.readPage(id, number) ?: hentaiService.fetchPage(id, number)
            .onSuccess { it?.also { page -> hentaiCache.writePage(id, number, page) } }
            .getOrNull()
        )?.let { HentaiPageInfo(id, number, it) }
}