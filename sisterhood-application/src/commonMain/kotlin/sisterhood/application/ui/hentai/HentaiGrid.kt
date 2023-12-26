package sisterhood.application.ui.hentai

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import sisterhood.presentation.HentaiInfo

@Composable
fun HentaiGrid(
    columns: Int,
    onPressItem: (HentaiInfo) -> Unit,
    state: HentaiGridState = rememberHentaiGridState()
) {
    val lazyGridState = state.lazyGridState

    LaunchedEffect(lazyGridState.canScrollForward) {
        state.onFetchMoreIds()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        state = lazyGridState,
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(state.hentaiIds) { id ->
            HentaiItem(id, state::fetchInfo, state::fetchThumbnail, onPressItem)
        }
    }
}
