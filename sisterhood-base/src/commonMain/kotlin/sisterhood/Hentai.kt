package sisterhood

import kotlinx.datetime.Instant

class Hentai constructor(
    val id: Int,
    val title: String,
    val language: HentaiLanguage,
    val createdAt: Instant
)
