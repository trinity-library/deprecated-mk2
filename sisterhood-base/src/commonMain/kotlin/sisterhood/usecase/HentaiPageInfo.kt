package sisterhood.usecase

import kotlinx.serialization.Serializable
import sisterhood.domain.HentaiPage
import sisterhood.domain.ImageSize

@Serializable
data class HentaiPageInfo(
    val size: ImageSize,
) {
    constructor(hentaiPage: HentaiPage) : this(hentaiPage.size)
}
