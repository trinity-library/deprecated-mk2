package sisterhood

import kotlinx.datetime.Instant

class Hentai(
    val id: HentaiId,
    val title: String,
    val language: HentaiLanguage,
    val createdAt: Instant
)
