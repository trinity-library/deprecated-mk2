package sisterhood.hentai.domain.ehentai

import kotlinx.serialization.Serializable

@Serializable
data class EHentaiTorrent(
    val hash: String,
    val added: String,
    val name: String,
    val tsize: String,
    val fsize: String,
)
