package sisterhood.application.hentai.viewer

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiViewerComponent(hentai: HentaiInfo) {
    val store = remember { HentaiViewerComponentStore() }

    Column {
        hentai.pages.forEachIndexed { pageNumber, page ->
            HentaiPage(hentai.id, pageNumber, page.size.ratio, store::fetchPage)
        }
    }
}
