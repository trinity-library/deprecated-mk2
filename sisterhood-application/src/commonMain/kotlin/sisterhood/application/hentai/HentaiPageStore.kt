package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.usecase.HentaiUnitOfWork

internal class HentaiPageStore(
    private val uow: HentaiUnitOfWork = Dependency.createHentaiUnitOfWork()
) {
    var state: HentaiPageState by mutableStateOf(HentaiPageState())
        private set

    suspend fun fetchInfo(id: HentaiId) = uow.selectOrFetchAndInsertHentai(id)

    suspend fun fetchThumbnail(id: HentaiId) = uow.readOrFetchAndWriteThumbnail(id)

    suspend fun onRefresh() = uow.fetchIds(HentaiLanguage.KOREAN, 0, 10).let { state = state.copy(ids = it) }

    data class HentaiPageState(
        val ids: List<HentaiId> = emptyList()
    )
}
