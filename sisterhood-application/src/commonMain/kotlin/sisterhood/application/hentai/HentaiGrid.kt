package sisterhood.application.hentai

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import sisterhood.domain.HentaiId
import sisterhood.usecase.HentaiInfo
import sisterhood.usecase.HentaiThumbnailInfo

@Composable
fun HentaiGrid(
    ids: List<HentaiId>,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiThumbnailInfo?,
    onRefresh: suspend () -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState.canScrollForward) {
        onRefresh()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(ids) { id ->
            HentaiItem(id, fetchInfo, fetchThumbnail)
        }
    }
}
