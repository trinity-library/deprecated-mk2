package sisterhood.domain

interface HentaiService {
    suspend fun fetchIds(language: HentaiLanguage, offset: Int, limit: Int): Result<List<HentaiId>>
    suspend fun fetchHentai(id: HentaiId): Result<Hentai?>
    suspend fun fetchThumbnail(id: HentaiId): Result<HentaiImage?>
    suspend fun fetchPage(id: HentaiId, number: Int): Result<HentaiImage?>
}
