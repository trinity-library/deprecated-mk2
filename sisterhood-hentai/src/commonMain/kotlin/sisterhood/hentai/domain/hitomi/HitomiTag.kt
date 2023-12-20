package sisterhood.hentai.domain.hitomi

import kotlinx.serialization.Serializable

@Serializable
data class HitomiTag(
    val tag: String,
    val female: String = "",
    val male: String = "",
)
