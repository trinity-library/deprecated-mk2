package sisterhood.hentai.ehentai

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

class EHentaiClientTest : ExpectSpec() {
    init {
        expect("can retrieve e-hentai gallery") {
            val httpClient = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }
            val client = EHentaiClient(httpClient)
            val galleries = client.requestGalleries(listOf(EHentaiGalleryToken(2231376L, "a7584a5932")))
            galleries.size shouldBeExactly 1
            galleries[0].gid shouldBeExactly 2231376L
            galleries[0].token shouldBe "a7584a5932"
        }

        expect("can retrieve e-hentai gallery token") {
            val httpClient = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }
            val client = EHentaiClient(httpClient)
            val tokens = client.requestTokens(listOf(EHentaiPageToken(618395L, "40bc07a79a", 11)))
            tokens.size shouldBeExactly 1
            tokens[0] shouldBe "0439fa3666"
        }
    }
}
