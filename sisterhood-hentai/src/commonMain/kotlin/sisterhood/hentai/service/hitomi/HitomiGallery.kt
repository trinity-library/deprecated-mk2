package sisterhood.hentai.service.hitomi

import kotlinx.datetime.toKotlinInstant
import kotlinx.serialization.Serializable
import sisterhood.domain.Hentai
import java.time.Instant
import java.time.format.DateTimeFormatter

const val DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ssX"

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
) {
    fun toHentai(): Hentai =
        Hentai(
            id,
            title,
            language.toHentaiLanguage(),
            files.map { it.toHentaiPage() },
            Instant.from(DateTimeFormatter.ofPattern(DATETIME_FORMAT).parse(date)).toKotlinInstant()
        )
}
