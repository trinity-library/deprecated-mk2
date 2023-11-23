package sisterhood.hentai.service.hitomi

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HitomiArtist(
    @SerialName("artist")
    val name: String
)
