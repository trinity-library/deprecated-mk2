package sisterhood.presentation

import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage
import sisterhood.domain.HentaiLanguage

interface HentaiUnitOfWork {
    suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int): List<HentaiId>
    suspend fun fetchHentai(id: HentaiId): HentaiInfo?
    suspend fun fetchPage(id: HentaiId, number: Int): HentaiImage?
    suspend fun fetchThumbnail(id: HentaiId): HentaiImage?
}
