package sisterhood.hentai.presentation

import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.hentai.business.HitomiService
import sisterhood.hentai.domain.hitomi.HitomiLanguage
import sisterhood.presentation.HentaiInfo
import sisterhood.presentation.HentaiUnitOfWork

class ConcreteHentaiUnitOfWork(
    private val hitomiService: HitomiService
) : HentaiUnitOfWork {
    override suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int) =
        hitomiService.fetchIds(HitomiLanguage.from(language), offset, limit).getOrDefault(emptyList())

    override suspend fun fetchHentai(id: HentaiId) =
        hitomiService.fetchGallery(id)?.toHentai()?.let { HentaiInfo(it) }

    override suspend fun fetchPage(id: HentaiId, number: Int) =
        hitomiService.fetchPage(id, number)

    override suspend fun fetchThumbnail(id: HentaiId) =
        hitomiService.fetchThumbnail(id)
}
