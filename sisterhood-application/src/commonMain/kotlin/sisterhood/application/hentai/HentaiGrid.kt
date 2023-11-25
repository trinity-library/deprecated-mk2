package sisterhood.application.hentai

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import sisterhood.HentaiId
import sisterhood.HentaiImage

@Composable
fun HentaiGrid(
    ids: List<HentaiId>,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiImage,
    onRefresh: suspend () -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState.canScrollForward) {
        onRefresh()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState
    ) {
        items(ids) { id ->
            HentaiItem(id, fetchInfo, fetchThumbnail)
        }
    }
}
