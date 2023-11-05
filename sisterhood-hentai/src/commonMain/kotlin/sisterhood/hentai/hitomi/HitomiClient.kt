package sisterhood.hentai.hitomi

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
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
        val route = "${gg.b}${gg.s(hash)}"
        return when (extension) {
            HitomiImageExtension.AVIF -> "https://${aOrb}a.hitomi.la/avif/$route/$hash.avif"
            HitomiImageExtension.JXL -> "https://${aOrb}a.hitomi.la/jxl/$route/$hash.jxl"
            HitomiImageExtension.WEBP -> "https://${aOrb}a.hitomi.la/webp/$route/$hash.webp"
        }
    }

    private fun thumbnailURLString(
        hash: String,
        extension: HitomiImageExtension,
        thumbnailSize: HitomiThumbnailSize
    ): String {
        val processedHash = "${hash.last()}/${hash.substring(hash.length - 3, hash.length - 1)}"
        val size = thumbnailSize.name.lowercase()
        return when (extension) {
            HitomiImageExtension.AVIF -> "https://atn.hitomi.la/avif${size}tn/$processedHash/$hash.avif"
            HitomiImageExtension.JXL -> "https://atn.hitomi.la/jxl${size}tn/$processedHash/$hash.jxl"
            HitomiImageExtension.WEBP -> "https://atn.hitomi.la/webp${size}tn/$processedHash/$hash.webp"
        }
    }

    suspend fun requestGallery(id: Int): HitomiGallery {
        val response = httpClient.get("https://ltn.hitomi.la/galleries/$id.js")
        val galleryJs = response.bodyAsText()
        val expectedPrefix = "var galleryinfo = "
        if (!galleryJs.startsWith(expectedPrefix)) {
            throw Error("Unexpected gallery response: $galleryJs")
        }
        return json.decodeFromString<HitomiGallery>(galleryJs.removePrefix(expectedPrefix))
    }

    suspend fun requestGalleryIDs(language: String, offset: Int, limit: Int): List<Int> {
        val response = httpClient.get("https://ltn.hitomi.la/index-$language.nozomi") {
            headers {
                if (offset != 0 || limit != 0) {
                    append(HttpHeaders.Range, "bytes=${offset * 4}-${offset * 4 + limit * 4 - 1}")
                }
            }
        }

        val byteArray: ByteArray = response.body()
        val intBuffer = ByteBuffer.wrap(byteArray).asIntBuffer()
        return (0..<(byteArray.size / 4))
            .map { idx -> intBuffer.get(idx) }
            .sortedDescending()
    }

    suspend fun requestPage(
        id: Int,
        pageHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP
    ): ByteArray =
        httpClient.get(pageURLString(pageHash, extension)) {
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
        }.readBytes()

    suspend fun requestThumbnail(
        id: Int,
        thumbnailHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP,
        thumbnailSize: HitomiThumbnailSize = HitomiThumbnailSize.SMALLBIG
    ): ByteArray =
        httpClient.get(thumbnailURLString(thumbnailHash, extension, thumbnailSize)) {
            headers {
                append(HttpHeaders.Referrer, "https://hitomi.la/reader/${id}.html")
            }
        }.readBytes()
}
