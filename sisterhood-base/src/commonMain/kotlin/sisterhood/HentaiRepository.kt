package sisterhood

interface HentaiRepository {
    suspend fun insert(hentai: Hentai)
    suspend fun select(vararg ids: Int): List<Hentai>
}
