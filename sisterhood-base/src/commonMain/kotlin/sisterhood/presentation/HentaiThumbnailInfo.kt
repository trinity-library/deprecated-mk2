package sisterhood.presentation

import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

data class HentaiThumbnailInfo(
    val id: HentaiId,
    val thumbnail: HentaiImage
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HentaiThumbnailInfo

        if (id != other.id) return false
        if (!thumbnail.contentEquals(other.thumbnail)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + thumbnail.contentHashCode()
        return result
    }
}
