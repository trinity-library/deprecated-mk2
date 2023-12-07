package sisterhood.application.hentai.viewer

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember

@Composable
fun HentaiPageList(
    state: HentaiPageListState
) {
    val hentaiInfo = state.hentaiInfo
    val lazyListState = state.lazyListState

    val firstVisibleItemIndex = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    LaunchedEffect(firstVisibleItemIndex) {
        firstVisibleItemIndex.apply {
            state.fetchPage(hentaiInfo.id, value)
        }
    }

    LazyColumn(state = lazyListState) {
        itemsIndexed(hentaiInfo.pages) { pageNumber, page ->
            HentaiPage(hentaiInfo.id, pageNumber, page.size.ratio, state::fetchPage)
        }
    }
}
