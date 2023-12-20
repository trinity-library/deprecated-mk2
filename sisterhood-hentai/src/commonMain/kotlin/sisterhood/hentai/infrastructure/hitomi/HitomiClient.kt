package sisterhood.hentai.infrastructure.hitomi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import sisterhood.domain.HentaiId
import sisterhood.hentai.domain.hitomi.HitomiGallery
import sisterhood.hentai.domain.hitomi.HitomiImageExtension
import sisterhood.hentai.domain.hitomi.HitomiLanguage
import sisterhood.hentai.domain.hitomi.HitomiThumbnailSize
import sisterhood.hentai.infrastructure.getResult
import java.nio.ByteBuffer

class HitomiClient(private val httpClient: HttpClient) {
    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        coerceInputValues = true
        decodeEnumsCaseInsensitive = true
        ignoreUnknownKeys = true
        isLenient = true
    }

    private suspend fun requestGG(): GG =
        httpClient.get("https://ltn.hitomi.la/gg.js").bodyAsText().let { GG.fromGGJs(it) }

    private suspend fun pageURLString(hash: String, extension: HitomiImageExtension): String {
        val gg = requestGG()

        val aOrb = Char(97 + gg.m(gg.s(hash)))
        val ext = extension.name.lowercase()
        val route = "${gg.b}${gg.s(hash)}"
        return "https://${aOrb}a.hitomi.la/$ext/$route/$hash.$ext"
    }

    private suspend fun thumbnailUrlString(
        hash: String,
        extension: HitomiImageExtension,
        thumbnailSize: HitomiThumbnailSize
    ): String {
        val gg = requestGG()

        val aOrb = Char(97 + gg.m(gg.s(hash)))
        val ext = extension.name.lowercase()
        val processedHash = "${hash.last()}/${hash.substring(hash.length - 3, hash.length - 1)}"
        val size = thumbnailSize.name.lowercase()
        return "https://${aOrb}tn.hitomi.la/$ext${size}tn/$processedHash/$hash.$ext"
    }

    suspend fun requestGallery(id: HentaiId): Result<HitomiGallery?> =
        httpClient.getResult("https://ltn.hitomi.la/galleries/$id.js")
            .mapCatching { response ->
                if (response.status == HttpStatusCode.NotFound) {
                    null
                } else {
                    val expectedPrefix = "var galleryinfo = "
                    val galleryJs = response.bodyAsText()
                    if (!galleryJs.startsWith(expectedPrefix)) {
                        throw Error("Unexpected gallery response: $galleryJs")
                    }
                    json.decodeFromString<HitomiGallery>(galleryJs.removePrefix(expectedPrefix))
                }
            }

    suspend fun requestGalleryIds(language: HitomiLanguage, offset: Int, limit: Int): Result<List<HentaiId>> =
        httpClient.getResult("https://ltn.hitomi.la/index-${language.name.lowercase()}.nozomi") {
            headers {
                if (offset != 0 || limit != 0) {
                    append(HttpHeaders.Range, "bytes=${offset * 4}-${offset * 4 + limit * 4 - 1}")
                }
            }
        }.mapCatching { response ->
            val byteArray: ByteArray = response.body()
            val intBuffer = ByteBuffer.wrap(byteArray).asIntBuffer()
            (0..<(byteArray.size / 4))
                .map { idx -> intBuffer.get(idx).toLong() }
                .sortedDescending()
        }


    suspend fun requestPage(
        id: HentaiId,
        pageHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP
    ): Result<ByteArray> =
        httpClient.getResult(pageURLString(pageHash, extension)) {
            headers {
                append(
                    HttpHeaders.Accept,
                    "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8"
                )
                append(HttpHeaders.Referrer, "https://hitomi.la/reader/${id}.html")
                append(
                    HttpHeaders.UserAgent,
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/109.0"
                )
            }
        }.mapCatching { response ->
            response.readBytes()
        }

    suspend fun requestThumbnail(
        thumbnailHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP,
        thumbnailSize: HitomiThumbnailSize = HitomiThumbnailSize.BIG
    ): Result<ByteArray?> =
        httpClient.getResult(thumbnailUrlString(thumbnailHash, extension, thumbnailSize)) {
            headers {
                append(HttpHeaders.Referrer, "https://hitomi.la")
            }
        }.mapCatching { response ->
            when (response.status) {
                HttpStatusCode.OK -> response.readBytes()
                else -> null
            }
        }
}
