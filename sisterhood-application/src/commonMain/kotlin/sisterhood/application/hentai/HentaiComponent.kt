package sisterhood.application.hentai

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiComponent(onPressItem: (HentaiInfo) -> Unit) {
    val store = remember { HentaiComponentStore() }

    HentaiGrid(
        ids = store.state.ids,
        fetchInfo = store::fetchInfo,
        fetchThumbnail = store::fetchThumbnail,
        onFetchMoreIds = store::onFetchMoreIds,
        onPressItem = onPressItem,
        onRefresh = store::onRefresh
    )
}
