package sisterhood.hentai.infrastructure.hitomi

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class HitomiServiceFactory {
    companion object {
        lateinit var instance: HitomiService
    }

    fun create(): HitomiService =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = HitomiService(HttpClient(CIO))
            instance
        }
}
