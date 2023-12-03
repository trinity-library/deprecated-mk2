package sisterhood.application.hentai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun HentaiComponent() {
    val store = remember { HentaiComponentStore() }

    HentaiGrid(store.state.ids, store::fetchInfo, store::fetchThumbnail, store::onFetchMoreIds, store::onRefresh)
}
