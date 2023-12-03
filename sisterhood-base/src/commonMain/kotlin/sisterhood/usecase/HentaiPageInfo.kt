package sisterhood.usecase

import sisterhood.domain.HentaiPage
import sisterhood.domain.ImageSize

data class HentaiPageInfo(
    val size: ImageSize,
) {
    constructor(hentaiPage: HentaiPage) : this(hentaiPage.size)
}
