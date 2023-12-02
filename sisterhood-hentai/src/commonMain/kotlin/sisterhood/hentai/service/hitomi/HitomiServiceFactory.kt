package sisterhood.hentai.service.hitomi

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class HitomiServiceFactory {
    fun create(): HitomiService {
        val httpClient = HttpClient(CIO)
        return HitomiService(httpClient)
    }
}
