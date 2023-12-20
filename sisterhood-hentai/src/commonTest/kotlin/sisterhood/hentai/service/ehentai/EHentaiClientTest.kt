package sisterhood.hentai.service.ehentai

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import sisterhood.hentai.domain.ehentai.EHentaiGalleryToken
import sisterhood.hentai.domain.ehentai.EHentaiPageToken

class EHentaiClientTest : ExpectSpec() {
    private val client = EHentaiClient(HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    })

    init {
        expect("can retrieve e-hentai gallery") {
            // Given
            val gid = 2231376
            val token = "a7584a5932"

            // When
            val galleries = client.requestGalleries(listOf(EHentaiGalleryToken(2231376, "a7584a5932")))

            // Then
            galleries.size shouldBeExactly 1
            galleries[0].id shouldBeExactly gid
            galleries[0].token shouldBe token
        }

        expect("can retrieve e-hentai gallery token") {
            // Given
            val gid = 618395
            val token = "40bc07a79a"
            val page = 11

            // When
            val tokens = client.requestTokens(listOf(EHentaiPageToken(gid, token, page)))

            //Then
            tokens.size shouldBeExactly 1
            tokens[0] shouldBe "0439fa3666"
        }
    }
}
