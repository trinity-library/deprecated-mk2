package sisterhood.hentai.service

import io.ktor.client.*
import kotlinx.datetime.toKotlinInstant
import sisterhood.Hentai
import sisterhood.HentaiService
import sisterhood.hentai.service.hitomi.HitomiClient
import java.time.Instant
import java.time.format.DateTimeFormatter

class KtorHentaiService(httpClient: HttpClient) : HentaiService {
    private val hitomi = HitomiClient(httpClient)

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX")

    override suspend fun fetch(id: Int): Result<Hentai?> =
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
}
