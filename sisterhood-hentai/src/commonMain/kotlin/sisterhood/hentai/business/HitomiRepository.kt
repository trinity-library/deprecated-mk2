package sisterhood.hentai.business

import sisterhood.domain.HentaiId
import sisterhood.hentai.domain.hitomi.HitomiGallery

interface HitomiRepository {
    suspend fun insertGallery(gallery: HitomiGallery)
    suspend fun selectGallery(id: HentaiId): HitomiGallery?
}
