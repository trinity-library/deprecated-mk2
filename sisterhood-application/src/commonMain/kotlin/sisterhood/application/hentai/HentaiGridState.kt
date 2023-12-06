package sisterhood.application.hentai

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.*
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.usecase.HentaiUnitOfWork

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
        ids = initialIds,
    )
}

@Stable
class HentaiGridState(
    firstVisibleItemIndex: Int,
    firstVisibleItemScrollOffset: Int,
    ids: List<HentaiId>,
    private val uow: HentaiUnitOfWork = Dependency.createHentaiUnitOfWork()
) {
    val lazyGridState by mutableStateOf(LazyGridState(firstVisibleItemIndex, firstVisibleItemScrollOffset))

    var ids by mutableStateOf(ids)

    suspend fun fetchInfo(id: HentaiId) = uow.selectOrFetchAndInsertHentai(id)

    suspend fun fetchThumbnail(id: HentaiId) = uow.readOrFetchAndWriteThumbnail(id)

    suspend fun onFetchMoreIds() {
        val offset = ids.size
        val breadcrumb = FETCH_SIZE - offset % FETCH_SIZE
        val fetchSize = FETCH_SIZE + breadcrumb
        val fetched = uow.fetchIds(HentaiLanguage.KOREAN, offset, fetchSize)
        val overlapped = ids.intersect(fetched)
        ids += fetched.minus(overlapped)
    }

    suspend fun onRefresh() =
        uow.fetchIds(HentaiLanguage.KOREAN, 0, FETCH_SIZE).let { ids = it }
}
