package sisterhood

interface HentaiService {
    suspend fun fetch(id: Int): Result<Hentai?>
}
