package sisterhood.hentai.service.hitomi

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.longs.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.ktor.client.*

class HitomiClientTest : ExpectSpec() {
    private val client = HitomiClient(HttpClient())

    init {
        expect("can retrieve a gallery") {
            // Given
            val galleryId = 2726888L

            // When
            val gallery = client.requestGallery(galleryId).getOrThrow()!!

            // Then
            gallery.id shouldBeExactly galleryId
        }

        expect("retrieve null if the gallery with given id doesn't exist") {
            // Given
            val galleryId = 0L

            // When
            val gallery = client.requestGallery(galleryId).getOrThrow()

            // Then
            gallery shouldBe null
        }

        expect("can retrieve gallery ids") {
            // Given
            val offset = 123
            val limit = 456

            // When
            val galleryIds = client.requestGalleryIds("all", offset, limit).getOrThrow()

            // Then
            galleryIds.size shouldBeExactly limit
        }

        expect("can retrieve pages with each extensions") {
            // Given
            val galleryId = 2726888L
            val pageHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val pages = HitomiImageExtension.entries.map { extension ->
                client.requestPage(galleryId, pageHash, extension).getOrThrow()
            }

            // Then
            pages.size shouldBeExactly HitomiImageExtension.entries.size
            pages.all { it.isNotEmpty() } shouldBe true
        }

        expect("can retrieve thumbnails with each extensions and sizes") {
            // Given
            val galleryId = 2726888L
            val thumbnailHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val thumbnails = HitomiImageExtension.entries.flatMap { extension ->
                HitomiThumbnailSize.entries.map { thumbnailSize ->
                    client.requestThumbnail(galleryId, thumbnailHash, extension, thumbnailSize).getOrThrow()!!
                }
            }

            // Then
            thumbnails.size shouldBeExactly HitomiImageExtension.entries.size * HitomiThumbnailSize.entries.size
            thumbnails.all { it.isNotEmpty() } shouldBe true
        }
    }
}
