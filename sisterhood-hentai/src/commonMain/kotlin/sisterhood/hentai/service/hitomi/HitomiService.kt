package sisterhood.hentai.service.hitomi

import io.ktor.client.*
import kotlinx.datetime.toKotlinInstant
import sisterhood.*
import java.time.Instant
import java.time.format.DateTimeFormatter

class HitomiService(httpClient: HttpClient) : HentaiService {
    private val hitomi = HitomiClient(httpClient)

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX")

    override suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int): Result<List<HentaiId>> =
        hitomi.requestGalleryIds(HitomiLanguage.from(language), offset, limit)

    override suspend fun fetchHentai(id: HentaiId): Result<Hentai?> =
        hitomi.requestGallery(id).mapCatching {
            it?.let {
                Hentai(
                    it.id,
                    it.title,
                    it.language.toHentaiLanguage(),
                    Instant.from(formatter.parse(it.date)).toKotlinInstant()
                )
            }
        }

    override suspend fun fetchThumbnail(id: HentaiId): Result<HentaiImage> =
        hitomi.requestGallery(id).mapCatching {
            it!!.let { gallery ->
                hitomi.requestThumbnail(id, gallery.files.first().hash).getOrThrow()
            }
        }
}
