package sisterhood.hentai.business

import sisterhood.domain.HentaiId
import sisterhood.hentai.domain.hitomi.HitomiGallery
import sisterhood.hentai.domain.hitomi.HitomiImageExtension
import sisterhood.hentai.domain.hitomi.HitomiLanguage
import sisterhood.hentai.domain.hitomi.HitomiThumbnailSize

interface HitomiClient {
    suspend fun requestGallery(id: HentaiId): Result<HitomiGallery?>

    suspend fun requestGalleryIds(
        language: HitomiLanguage,
        offset: Int,
        limit: Int
    ): Result<List<HentaiId>>


    suspend fun requestPage(
        id: HentaiId,
        pageHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP
    ): Result<ByteArray>

    suspend fun requestThumbnail(
        thumbnailHash: String,
        extension: HitomiImageExtension = HitomiImageExtension.WEBP,
        thumbnailSize: HitomiThumbnailSize = HitomiThumbnailSize.BIG
    ): Result<ByteArray?>
}
