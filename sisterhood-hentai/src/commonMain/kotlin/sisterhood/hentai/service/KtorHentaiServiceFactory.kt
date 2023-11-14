package sisterhood.hentai.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class KtorHentaiServiceFactory {
    fun create(): KtorHentaiService {
        val httpClient = HttpClient(CIO)
        return KtorHentaiService()
    }
}
