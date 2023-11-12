package sisterhood

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

class Hentai constructor(
    val id: Int,
    val title: String,
    val fetchedAt: Instant = Clock.System.now(),
)
