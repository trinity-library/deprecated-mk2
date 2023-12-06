package sisterhood.application.hentai.viewer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiViewerComponent(hentai: HentaiInfo) {
    val store = remember { HentaiViewerComponentStore() }

    val state = rememberLazyListState()

    LazyColumn(state = state) {
        itemsIndexed(hentai.pages) { pageNumber, page ->
            HentaiPage(hentai.id, pageNumber, page.size.ratio, store::fetchPage)
        }
    }
}
