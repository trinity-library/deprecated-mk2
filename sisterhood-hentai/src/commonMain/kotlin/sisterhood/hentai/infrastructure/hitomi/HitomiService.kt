package sisterhood.hentai.infrastructure.hitomi

import io.ktor.client.*
import sisterhood.domain.*
import sisterhood.hentai.domain.hitomi.HitomiLanguage

class HitomiService(httpClient: HttpClient) : HentaiService {
    private val hitomi = HitomiClient(httpClient)

    override suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int): Result<List<HentaiId>> =
        hitomi.requestGalleryIds(HitomiLanguage.from(language), offset, limit)

    override suspend fun fetchHentai(id: HentaiId): Result<Hentai?> =
        hitomi.requestGallery(id).mapCatching { it?.toHentai() }

    override suspend fun fetchThumbnail(id: HentaiId): Result<HentaiImage?> =
        hitomi.requestGallery(id).mapCatching {
            it!!.let { gallery ->
                hitomi.requestThumbnail(gallery.files.first().hash).getOrThrow()
            }
        }

    override suspend fun fetchPage(id: HentaiId, number: Int): Result<HentaiImage?> =
        hitomi.requestGallery(id).mapCatching {
            it!!.let { gallery ->
                hitomi.requestPage(id, gallery.files[number].hash).getOrThrow()
            }
        }
}
