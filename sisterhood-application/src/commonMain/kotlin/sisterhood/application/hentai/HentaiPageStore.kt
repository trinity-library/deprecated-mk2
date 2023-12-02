package sisterhood.application.hentai

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import sisterhood.application.Dependency
import sisterhood.domain.HentaiId
import sisterhood.domain.HentaiLanguage
import sisterhood.domain.HentaiService
import sisterhood.usecase.HentaiInfo

internal class HentaiPageStore(
    private val hentaiService: HentaiService = Dependency.createHentaiService()
) {
    var state: HentaiPageState by mutableStateOf(HentaiPageState())
        private set

    suspend fun fetchInfo(id: HentaiId) =
        hentaiService
            .fetchHentai(id)
            .getOrNull()
            ?.let { HentaiInfo(it.id, it.title, it.language.name) }

    suspend fun fetchThumbnail(id: HentaiId) =
        hentaiService
            .fetchThumbnail(id)
            .getOrDefault(ByteArray(0))

    suspend fun onRefresh() =
        hentaiService
            .fetchIds(HentaiLanguage.KOREAN, 0, 10)
            .getOrDefault(state.ids)
            .let { state = state.copy(ids = it) }

    data class HentaiPageState(
        val ids: List<HentaiId> = emptyList()
    )
}
