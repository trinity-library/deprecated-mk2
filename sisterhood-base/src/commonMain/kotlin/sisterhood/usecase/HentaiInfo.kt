package sisterhood.usecase

import sisterhood.domain.Hentai
import sisterhood.domain.HentaiId

data class HentaiInfo(
    val id: HentaiId,
    val title: String,
    val language: String
) {
    constructor(hentai: Hentai) : this(hentai.id, hentai.title, hentai.language.name)
}
