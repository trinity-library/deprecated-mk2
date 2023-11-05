package sisterhood.hentai.hitomi

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import io.ktor.client.*

class HitomiClientTest : ExpectSpec() {
    private val client = HitomiClient(HttpClient())

    init {
        expect("can retrieve a gallery") {
            // Given
            val galleryID = 2726888

            // When
            val gallery = client.requestGallery(galleryID)

            // Then
            gallery.id shouldBeExactly galleryID
        }

        expect("can retrieve gallery ids") {
            // Given
            val offset = 123
            val limit = 456

            // When
            val galleryIds = client.requestGalleryIDs("all", offset, limit)

            // Then
            galleryIds.size shouldBeExactly limit
        }

        expect("can retrieve pages with each extensions") {
            // Given
            val galleryID = 2726888
            val pageHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val pages = HitomiImageExtension.entries.map { extension ->
                client.requestPage(galleryID, pageHash, extension)
            }

            // Then
            pages.size shouldBeExactly HitomiImageExtension.entries.size
            pages.all { it.isNotEmpty() } shouldBe true
        }

        expect("can retrieve thumbnails with each extensions and sizes") {
            // Given
            val galleryID = 2726888
            val thumbnailHash = "02251ff411fd9a78ac3a3ade18551e060e5c22a696b83d02af08da6294283573"

            // When
            val thumbnails = HitomiImageExtension.entries.flatMap { extension ->
                HitomiThumbnailSize.entries.map { thumbnailSize ->
                    client.requestThumbnail(galleryID, thumbnailHash, extension, thumbnailSize)
                }
            }

            // Then
            thumbnails.size shouldBeExactly HitomiImageExtension.entries.size * HitomiThumbnailSize.entries.size
            thumbnails.all { it.isNotEmpty() } shouldBe true
        }
    }
}
