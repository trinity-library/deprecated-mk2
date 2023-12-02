package sisterhood.usecase

import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiImage

data class HentaiPageInfo(
    val id: HentaiId,
    val number: Int,
    val page: HentaiImage
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HentaiPageInfo

        if (id != other.id) return false
        if (number != other.number) return false
        if (!page.contentEquals(other.page)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + number
        result = 31 * result + page.contentHashCode()
        return result
    }
}
