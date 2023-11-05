package sisterhood.hentai.ehentai

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

const val EHENTAI_API_URL = "https://api.e-hentai.org/api.php"

class EHentaiClient(private val httpClient: HttpClient) {
    private val json = Json {
        coerceInputValues = true
        ignoreUnknownKeys = true
        isLenient = true
    }

    suspend fun requestGalleries(galleryTokens: List<EHentaiGalleryToken>): List<EHentaiGallery> {
        val response = httpClient.post(EHENTAI_API_URL) {
            contentType(ContentType.Application.Json)
            setBody(EHentaiGalleryRequest(galleryTokens, true))
        }

        return try {
            response.body<EHentaiGalleryResponse>().gmetadata
        } catch (e: NoTransformationFoundException) {
            json.decodeFromString<EHentaiGalleryResponse>(response.bodyAsText()).gmetadata
        }
    }


    suspend fun requestTokens(pageTokens: List<EHentaiPageToken>): List<String> {
        val response = httpClient.post(EHENTAI_API_URL) {
            contentType(ContentType.Application.Json)
            setBody(EHentaiGalleryTokenRequest(pageTokens))
        }

        return try {
            response.body<EHentaiGalleryTokenResponse>().tokenlist.map { it.token }
        } catch (e: NoTransformationFoundException) {
            json.decodeFromString<EHentaiGalleryTokenResponse>(response.bodyAsText()).tokenlist.map { it.token }
        }
    }
}
