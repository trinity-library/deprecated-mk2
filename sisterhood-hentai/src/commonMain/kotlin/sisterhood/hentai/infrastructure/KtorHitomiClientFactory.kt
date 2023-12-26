package sisterhood.hentai.infrastructure

import io.ktor.client.*
import io.ktor.client.engine.cio.*

class KtorHitomiClientFactory {
    companion object {
        lateinit var instance: KtorHitomiClient
    }

    fun create(): KtorHitomiClient =
        try {
            instance
        } catch (_: UninitializedPropertyAccessException) {
            instance = KtorHitomiClient(HttpClient(CIO))
            instance
        }
}
