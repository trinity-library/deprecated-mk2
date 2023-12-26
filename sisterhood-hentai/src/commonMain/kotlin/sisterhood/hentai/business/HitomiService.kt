package sisterhood.hentai.business

import sisterhood.domain.HentaiId
import sisterhood.hentai.domain.hitomi.HitomiLanguage

class HitomiService(
    private val client: HitomiClient,
    private val cache: HitomiCache,
    private val repository: HitomiRepository
) {
    suspend fun fetchIds(language: HitomiLanguage, offset: Int, limit: Int) =
        client.requestGalleryIds(language, offset, limit)

    suspend fun fetchGallery(id: HentaiId) =
        repository.selectGallery(id) ?: client.requestGallery(id).getOrNull()
            ?.also { gallery -> repository.insertGallery(gallery) }

    suspend fun fetchPage(id: HentaiId, number: Int) =
        cache.readPage(id, number) ?: fetchGallery(id)?.files?.getOrNull(number)?.let { file ->
            client.requestPage(id, file.hash).getOrNull()?.also { page -> cache.writePage(id, number, page) }
        }

    suspend fun fetchThumbnail(id: HentaiId) =
        cache.readThumbnail(id) ?: fetchGallery(id)?.files?.firstOrNull()?.let { file ->
            client.requestThumbnail(file.hash).getOrNull()?.also { thumbnail -> cache.writeThumbnail(id, thumbnail) }
        }
}
