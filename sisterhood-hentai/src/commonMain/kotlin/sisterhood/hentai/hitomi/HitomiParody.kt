package sisterhood.hentai.hitomi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitomiParody(
    @SerialName("parody")
    val name: String
)
