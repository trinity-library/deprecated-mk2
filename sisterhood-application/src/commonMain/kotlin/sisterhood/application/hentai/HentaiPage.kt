package sisterhood.application.hentai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun HentaiPage() {
    val store = remember { HentaiPageStore() }

    HentaiGrid(store.state.ids, store::fetchInfo, store::fetchThumbnail, store::onRefresh)
}
