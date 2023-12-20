package sisterhood.hentai.service.hitomi

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.http.*
import sisterhood.hentai.domain.hitomi.HitomiImageExtension
import sisterhood.hentai.domain.hitomi.HitomiLanguage
import sisterhood.hentai.domain.hitomi.HitomiThumbnailSize
import java.io.File

class HitomiClientTest : ExpectSpec() {
    private val resourcesPath = "src/commonTest/resources"

    init {
        expect("can retrieve a gallery") {
            // Given
            val galleryId = 2726888L
            val hitomiClient = HitomiClient(
                HttpClient(
                    MockEngine {
                        respond(
                            content = File("$resourcesPath/gallery-$galleryId.js").readText(),
                            status = HttpStatusCode.OK,
                            headers = headers {
                                append(HttpHeaders.ContentType, "application/javascript; charset=UTF-8")
                            }
                        )
                    }
                )
            )

            // When
            val gallery = hitomiClient.requestGallery(galleryId).getOrThrow()!!

            // Then
            gallery.id shouldBeExactly galleryId
        }

        expect("retrieve null if the gallery with given id doesn't exist") {
            // Given
            val galleryId = 0L
            val hitomiClient = HitomiClient(
                HttpClient(
                    MockEngine {
                        respond(
                            content = File("$resourcesPath/gallery-not-found.html").readText(),
                            status = HttpStatusCode.NotFound,
                            headers = headers {
                                append(HttpHeaders.ContentType, "text/html; charset=UTF-8")
                            }
                        )
                    }
                )
            )

            // When
            val gallery = hitomiClient.requestGallery(galleryId).getOrThrow()

            // Then
            gallery shouldBe null
        }

        expect("can retrieve gallery ids") {
            // Given
            val offset = 123
            val limit = 456
            val hitomiClient = HitomiClient(
                HttpClient(
                    MockEngine {
                        respond(
                            content = File("$resourcesPath/id-index-$offset-$limit.nozomi").readBytes(),
                            status = HttpStatusCode.PartialContent,
                            headers = headers {
                                append(HttpHeaders.ContentLength, "1824")
                                append(HttpHeaders.ContentRange, "bytes 492-2315/3443404")
                                append(HttpHeaders.ContentType, "application/x-nozomi")
                            }
                        )
                    }
                )
            )

            // When
            val galleryIds = hitomiClient.requestGalleryIds(HitomiLanguage.ENGLISH, offset, limit).getOrThrow()

            // Then
            galleryIds.size shouldBeExactly limit
        }

        expect("can retrieve pages with each extensions") {
            // Given
            val galleryId = 2726888L
            val hitomiClient = HitomiClient(
                HttpClient(
                    MockEngine { request ->
                        if (request.url.toString() == "https://ltn.hitomi.la/gg.js") {
                            respond(
                                content = File("$resourcesPath/gg.js").readText(),
                                status = HttpStatusCode.OK,
                                headers = headers {
                                    append(HttpHeaders.ContentType, "application/javascript; charset=UTF-8")
                                }
                            )
                        } else {
                            respond(
                                content = ByteArray(123),
                                status = HttpStatusCode.OK
                            )
                        }
                    }
                )
            )
            val pageHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val pages = HitomiImageExtension.entries.map { extension ->
                hitomiClient.requestPage(galleryId, pageHash, extension).getOrThrow()
            }

            // Then
            pages.size shouldBeExactly HitomiImageExtension.entries.size
            pages.all { it.isNotEmpty() } shouldBe true
        }

        expect("can retrieve thumbnails with each extensions and sizes") {
            // Given
            val hitomiClient = HitomiClient(
                HttpClient(
                    MockEngine { request ->
                        if (request.url.toString() == "https://ltn.hitomi.la/gg.js") {
                            respond(
                                content = File("$resourcesPath/gg.js").readText(),
                                status = HttpStatusCode.OK,
                                headers = headers {
                                    append(HttpHeaders.ContentType, "application/javascript; charset=UTF-8")
                                }
                            )
                        } else {
                            respond(
                                content = ByteArray(123),
                                status = HttpStatusCode.OK
                            )
                        }
                    }
                )
            )
            val thumbnailHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val thumbnails = HitomiImageExtension.entries.flatMap { extension ->
                HitomiThumbnailSize.entries.map { thumbnailSize ->
                    hitomiClient.requestThumbnail(thumbnailHash, extension, thumbnailSize).getOrThrow()
                }
            }

            // Then
            thumbnails.size shouldBeExactly HitomiImageExtension.entries.size * HitomiThumbnailSize.entries.size
            thumbnails.all { it?.isNotEmpty() ?: false } shouldBe true
        }
    }
}
