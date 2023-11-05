package sisterhood.hentai.hitomi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitomiFile(
    val name: String,
    val hash: String,
    val width: Int,
    val height: Int,
    @SerialName("hasavif")
    val hasAvif: Int,
    @SerialName("hasjxl")
    val hasJxl: Int,
    @SerialName("haswebp")
    val hasWebp: Int
)
