package sisterhood

interface HentaiService {
    suspend fun fetch(id: HentaiId): Result<Hentai?>
}
