package sisterhood.usecase

import sisterhood.domain.*

class HentaiUnitOfWork(
    private val hentaiCache: HentaiCache,
    private val hentaiRepository: HentaiRepository,
    private val hentaiService: HentaiService
) {
    suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int): List<HentaiId> =
        hentaiService.fetchIds(language, offset, limit).getOrDefault(emptyList())

    suspend fun selectOrFetchAndInsertHentai(id: HentaiId): HentaiInfo? = (
        hentaiRepository.select(id).firstOrNull() ?: hentaiService.fetchHentai(id)
            .onSuccess { it?.also { hentai -> hentaiRepository.insert(hentai) } }
            .getOrNull()
        )?.let { HentaiInfo(it) }

    suspend fun readOrFetchAndWriteThumbnail(id: HentaiId): HentaiImage? =
        hentaiCache.readThumbnail(id) ?: hentaiService.fetchThumbnail(id)
            .onSuccess { it?.also { thumbnail -> hentaiCache.writeThumbnail(id, thumbnail) } }
            .getOrNull()

    suspend fun readOrFetchAndWritePage(id: HentaiId, number: Int): HentaiImage? =
        hentaiCache.readPage(id, number) ?: hentaiService.fetchPage(id, number)
            .onSuccess { it?.also { page -> hentaiCache.writePage(id, number, page) } }
            .getOrNull()
}
