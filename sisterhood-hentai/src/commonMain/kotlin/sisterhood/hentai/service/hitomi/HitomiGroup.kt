package sisterhood.hentai.service.hitomi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitomiGroup(
    @SerialName("group")
    val name: String
)
