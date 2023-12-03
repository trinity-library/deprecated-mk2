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
import sisterhood.domain.HentaiImage
import sisterhood.usecase.HentaiInfo

@Composable
fun HentaiGrid(
    ids: List<HentaiId>,
    fetchInfo: suspend (HentaiId) -> HentaiInfo?,
    fetchThumbnail: suspend (HentaiId) -> HentaiImage?,
    onFetchMoreIds: suspend () -> Unit,
    onRefresh: suspend () -> Unit
) {
    val lazyGridState = rememberLazyGridState()

    LaunchedEffect(lazyGridState.canScrollForward) {
        onFetchMoreIds()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = lazyGridState,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(ids) { id ->
            HentaiItem(id, fetchInfo, fetchThumbnail, {})
        }
    }
}
