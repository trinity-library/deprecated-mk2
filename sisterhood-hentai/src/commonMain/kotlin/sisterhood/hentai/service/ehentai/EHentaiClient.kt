package sisterhood.hentai.service.ehentai

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import sisterhood.hentai.domain.ehentai.EHentaiGallery
import sisterhood.hentai.domain.ehentai.EHentaiGalleryToken
import sisterhood.hentai.domain.ehentai.EHentaiPageToken

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
            response.body<EHentaiGalleryResponse>().galleries
        } catch (e: NoTransformationFoundException) {
            json.decodeFromString<EHentaiGalleryResponse>(response.bodyAsText()).galleries
        }
    }


    suspend fun requestTokens(pageTokens: List<EHentaiPageToken>): List<String> {
        val response = httpClient.post(EHENTAI_API_URL) {
            contentType(ContentType.Application.Json)
            setBody(EHentaiGalleryTokenRequest(pageTokens))
        }

        return try {
            response.body<EHentaiGalleryTokenResponse>().tokens.map { it.token }
        } catch (e: NoTransformationFoundException) {
            json.decodeFromString<EHentaiGalleryTokenResponse>(response.bodyAsText()).tokens.map { it.token }
        }
    }
}
