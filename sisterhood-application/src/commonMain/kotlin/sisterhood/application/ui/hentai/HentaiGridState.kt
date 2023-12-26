package sisterhood.application.ui.hentai

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.*
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.presentation.HentaiUnitOfWork

const val FETCH_SIZE = 10

@Composable
fun rememberHentaiGridState(
    initialFirstVisibleItemIndex: Int = 0,
    initialFirstVisibleItemScrollOffset: Int = 0,
    initialIds: List<HentaiId> = emptyList()
) = remember {
    HentaiGridState(

        firstVisibleItemIndex = initialFirstVisibleItemIndex,
        firstVisibleItemScrollOffset = initialFirstVisibleItemScrollOffset,
        hentaiIds = initialIds,
    )
}

@Stable
class HentaiGridState(
    firstVisibleItemIndex: Int,
    firstVisibleItemScrollOffset: Int,
    hentaiIds: List<HentaiId>,
    private val uow: HentaiUnitOfWork = Dependency.provideHentaiUnitOfWork(),
) {
    var hentaiIds by mutableStateOf(hentaiIds)
    val lazyGridState = LazyGridState(firstVisibleItemIndex, firstVisibleItemScrollOffset)

    suspend fun fetchInfo(id: HentaiId) = uow.fetchHentai(id)

    suspend fun fetchThumbnail(id: HentaiId) = uow.fetchThumbnail(id)

    suspend fun onFetchMoreIds() {
        val offset = hentaiIds.size
        val breadcrumb = FETCH_SIZE - offset % FETCH_SIZE
        val fetchSize = FETCH_SIZE + breadcrumb
        val fetched = uow.fetchIds(HentaiLanguage.KOREAN, offset, fetchSize)
        val overlapped = hentaiIds.intersect(fetched.toSet())
        hentaiIds += fetched.minus(overlapped)
    }
}
