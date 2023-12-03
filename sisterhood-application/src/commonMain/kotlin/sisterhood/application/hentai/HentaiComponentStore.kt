package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.usecase.HentaiUnitOfWork

const val FETCH_SIZE = 10

internal class HentaiComponentStore(
    private val uow: HentaiUnitOfWork = Dependency.createHentaiUnitOfWork()
) {
    var state: HentaiComponentState by mutableStateOf(HentaiComponentState())
        private set

    suspend fun fetchInfo(id: HentaiId) = uow.selectOrFetchAndInsertHentai(id)

    suspend fun fetchThumbnail(id: HentaiId) = uow.readOrFetchAndWriteThumbnail(id)

    suspend fun onFetchMoreIds() {
        val offset = state.ids.size
        val breadcrumb = FETCH_SIZE - offset % FETCH_SIZE
        val fetchSize = FETCH_SIZE + breadcrumb
        val fetched = uow.fetchIds(HentaiLanguage.KOREAN, offset, fetchSize)
        val overlapped = state.ids.intersect(fetched)
        state = state.copy(ids = state.ids + fetched.minus(overlapped))
    }

    suspend fun onRefresh() =
        uow.fetchIds(HentaiLanguage.KOREAN, 0, FETCH_SIZE).let { state = state.copy(ids = it) }

    data class HentaiComponentState(
        val ids: List<HentaiId> = emptyList()
    )
}
