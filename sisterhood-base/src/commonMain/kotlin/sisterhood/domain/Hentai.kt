package sisterhood.domain

import kotlinx.datetime.Instant

data class Hentai(
    val id: HentaiId,
    val title: String,
    val language: HentaiLanguage,
    val pages: List<HentaiPage>,
    val createdAt: Instant
)
