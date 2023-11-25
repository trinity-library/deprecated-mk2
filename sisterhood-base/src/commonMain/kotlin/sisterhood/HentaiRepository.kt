package sisterhood

interface HentaiRepository {
    suspend fun insert(hentai: Hentai)
    suspend fun select(vararg ids: HentaiId): List<Hentai>
}
