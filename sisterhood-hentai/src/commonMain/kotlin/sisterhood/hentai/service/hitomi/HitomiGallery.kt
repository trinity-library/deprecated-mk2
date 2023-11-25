package sisterhood.hentai.service.hitomi

import kotlinx.serialization.Serializable

@Serializable
data class HitomiGallery(
    val id: Long,
    val title: String,
    val language: HitomiLanguage = HitomiLanguage.NONE,
    val files: List<HitomiFile>,
    val date: String,
    val artists: List<HitomiArtist> = emptyList(),
    val characters: List<HitomiCharacter> = emptyList(),
    val groups: List<HitomiGroup> = emptyList(),
    val parodys: List<HitomiParody> = emptyList(),
    val related: List<Int> = emptyList(),
    val tags: List<HitomiTag> = emptyList()
)
