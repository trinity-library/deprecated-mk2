package sisterhood.application.hentai.viewer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.usecase.HentaiUnitOfWork

internal class HentaiViewerComponentStore(
    private val uow: HentaiUnitOfWork = Dependency.createHentaiUnitOfWork()
) {
    var state: HentaiViewerComponentState by mutableStateOf(HentaiViewerComponentState())
        private set

    suspend fun fetchPage(id: HentaiId, pageNumber: Int) = uow.readOrFetchAndWritePage(id, pageNumber)

    data class HentaiViewerComponentState(
        val lastWatched: Int = 0
    )
}
