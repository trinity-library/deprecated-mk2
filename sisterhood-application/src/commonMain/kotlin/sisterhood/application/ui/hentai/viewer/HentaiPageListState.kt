package sisterhood.application.ui.hentai.viewer

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.presentation.HentaiInfo
import sisterhood.presentation.HentaiUnitOfWork

@Composable
fun rememberHentaiPageListState(
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0,
    initialHentaiInfo: HentaiInfo,
) = remember {
    HentaiPageListState(
        initialFirstVisibleItemIndex,
        initialFirstVisibleItemScrollOffset,
        initialHentaiInfo,
    )
}

class HentaiPageListState(
    firstVisibleItemIndex: Int,
    firstVisibleItemScrollOffset: Int,
    val hentaiInfo: HentaiInfo,
    private val uow: HentaiUnitOfWork = Dependency.provideHentaiUnitOfWork()
) {
    val lazyListState = LazyListState(firstVisibleItemIndex, firstVisibleItemScrollOffset)

    suspend fun fetchPage(id: HentaiId, pageNumber: Int) = uow.fetchPage(id, pageNumber)
}
